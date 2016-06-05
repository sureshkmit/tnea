package in.healthycoder.tnea.cutoff;

import in.healthycoder.tnea.RetryOnFailure;
import in.healthycoder.tnea.college.College;
import in.healthycoder.tnea.college.Community;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Created by Suresh_Karuppannan on 5/27/2016.
 */
public class CutoffExtractor {

    private Random random = new Random();

    public Collection<Cutoff> getCutoff(College college) {
        HashMap<String, Cutoff> departments = new HashMap<>();
        Community.stream()
                .forEach(community -> {
                    try {
                        new RetryOnFailure<Void>(3).run(() -> processCutoffResponse(college, community, departments));
                        waitSometime();
                    } catch (Exception e) {
                        System.err.format("FAILED FOR EVER: %s, %s\n", college, community);
                    }
                });
        return departments.values();
    }

    private void waitSometime() {
        try {
            Thread.sleep(random.nextInt(5) * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Void processCutoffResponse(College college, Community community, Map<String, Cutoff> departments) {
        try {
            System.out.printf("%s,%s,%s %s started\n", college.getDistrict().getCode(), college.getCode(), college.getName(), community);
            Element table = getCutoffHtmlResponse(college, community);
            List<Community> communities = getCommunities(table);
            table.select("tr:gt(1)")
                    .forEach(e -> {
                        String branch = e.select("td:eq(0) span").text();
                        Cutoff cutoff = getOrCreateCutoff(college, branch, departments);
                        List<String> marks = e.select("td:gt(0) span").stream().map(element -> element.text()).collect(toList());
                        cutoff.setCutoff(communities, marks);
                    });
        } catch (Exception e) {
            throw new RuntimeException(String.format("Exception while processing cutoff response: %s, %s", college, community));
        }
        return null;
    }

    public Document getCutoffResponseByUrl(College college, Community community) throws IOException {
        return Jsoup.connect("http://univadm.annauniv.edu:7090/cutoff2015/cutoff.jsp")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                .data("dist", String.valueOf(college.getDistrict().getCode()))
                .data("collname", college.getCode())
                .data("comm", String.valueOf(community.getCode()))
                .post();
    }


    public Document getCutoffResponseFromFile(College college, Community community) {
        InputStream response = this.getClass().getClassLoader().getResourceAsStream("sample.html");
        Document doc = Jsoup.parse(convertStreamToString(response));
        System.out.println("doc = " + doc.toString());
        return doc;
    }


    private Element getCutoffHtmlResponse(College college, Community community) throws IOException {
        Document doc = getCutoffResponseByUrl(college, community);
        return doc.select("table[width=\"515\"] tbody").first();
    }

    public Cutoff getOrCreateCutoff(College college, String branch, Map<String, Cutoff> departments) {
        Cutoff cutoff = departments.get(branch);

        if (cutoff == null) {
            cutoff = new Cutoff(college, branch);
            departments.put(branch, cutoff);
        }
        return cutoff;
    }

    public List<Community> getCommunities(Element element) {
        return element.select("tr:eq(0) td:gt(0)")
                .select("font[color=\"yellow\"]")
                .stream()
                .map(e -> Community.valueOf(e.text()))
                .collect(toList());
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
