package in.healthycoder.tnea.college;

import in.healthycoder.tnea.CsvUtility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Suresh_Karuppannan on 5/24/2016.
 */
public class CollegeExtractor {
    public Stream<College> getColleges(int dcode) {
        List<College> colleges = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://univadm.annauniv.edu:7090/cutoff2015/coll.jsp?" + dcode)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                    .get();
            Elements options = doc.getElementsByTag("option");
            for (Element c : options) {
                String collegeCode = c.attr("value");
                String collegeName = c.text();
                College college = new College(collegeCode, collegeName, District.getValue(dcode));
                colleges.add(college);
            }
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return colleges.stream();
    }

    public Stream<College> getAllColleges() {
        return District.stream().flatMap(district -> getColleges(district.getCode()));
    }





}
