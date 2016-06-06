<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico" />


    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Ask Sabari</title>

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <%--<li role="presentation" class="active"><a href="#">Home</a></li>--%>
                <%--<li role="presentation"><a href="#">About</a></li>--%>
                <%--<li role="presentation"><a href="#">Contact</a></li>--%>
            </ul>
        </nav>
        <h3 class="text-muted">Ask Sabari</h3>
    </div>


    <div class="bg-info" style="margin: 0px">Want to know which engineering colleges will be available for your cutt of
        mark? Try the search below.
        Good luck!
    </div>

    <br/>

    <div class="row marketing" style="margin-left: 5px; margin-right: 5px">
        <form:form class="form-horizontal" method="POST" action="/">
            <div class="form-group">
                <form:label path="cutoff" for="inputCutoff" class="col-sm-2 control-label">Cutoff</form:label>
                <div class="col-sm-10">
                    <form:input path="cutoff" type="number" class="form-control" id="inputCutoff" placeholder="194.0"
                                autofocus="autofocus" required="required" min="1" max="200" step=".25"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="community" for="inputCommunity" class="col-sm-2 control-label">Community</form:label>
                <div class="col-sm-10">
                    <form:select path="community" class="form-control">
                        <form:options items="${communities}" itemLabel="name" itemValue="code"/>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <form:label path="branch" for="inputBranch" class="col-sm-2 control-label">Branch</form:label>
                <div class="col-sm-10">
                    <form:select path="branch" class="form-control">
                        <form:options items="${branches}"/>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-default" value="Find colleges"/>
                </div>
            </div>
        </form:form>


        <% if (request.getMethod().equalsIgnoreCase("post")) { %>

        <div class="panel panel-primary">
            <!-- Default panel contents -->
            <div class="panel-heading"><h3 class="panel-title">Colleges you might be interested</h3> </div>
            <c:if test="${empty result.colleges}">
                <div class="panel-body">
                    <p>No colleges found</p>
                </div>
            </c:if>


            <!-- List group -->
            <c:if test="${not empty result.colleges}">
                <ul class="list-group">
                    <c:forEach var="listValue" items="${result.colleges}">
                        <li class="list-group-item lead">${String.format("%6.2f%n", listValue.cutoff).replaceAll(" ", "&nbsp;")}&nbsp;&nbsp; ${listValue.name}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>


        <% }%>
    </div>
</div>

<footer class="footer">
    <%--<p>&copy; Data should not be used for commercial purpose.</p>--%>
</footer>

</div> <!-- /container -->


</body>
</html>