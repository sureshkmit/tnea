package in.healthycoder.tnea;

import in.healthycoder.tnea.college.College;
import in.healthycoder.tnea.college.CollegeReader;
import in.healthycoder.tnea.cutoff.Cutoff;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Suresh_Karuppannan on 5/29/2016.
 */
public class CutoffManager {
    public static final List<Cutoff> marks;
    public static final Map<String, College> colleges;

    static {
        colleges = new HashMap<>();
        marks = new ArrayList<>();
        CollegeReader reader = new CollegeReader();
        reader.readColleges()
                .forEach(college -> colleges.put(college.getCode(), college));
        read().forEach(cutoff -> marks.add(cutoff));
    }

    public static Stream<Cutoff> read() {
        try {
            InputStream is = CutoffManager.class.getClassLoader().getResourceAsStream("cutoff2015.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            return br.lines()
                    .skip(1)
                    .map(csv -> new Cutoff(csv, colleges));
        } catch (Exception e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
