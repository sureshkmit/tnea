<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Sabari Suggestions</title>
</head>
<body>

<h2>Colleges predicted:</h2>
<table>
    <tr>
        <td><h2>${search.branch}<h2></td>
    </tr>
    <tr>
        <td>
            <c:if test="${not empty colleges}">
                <ul>
                    <c:forEach var="listValue" items="${colleges}">
                        <li>${listValue.name}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </td>
    </tr>

</table>
</body>
</html>