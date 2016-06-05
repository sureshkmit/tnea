package in.healthycoder.tnea.cutoff;

import in.healthycoder.tnea.college.College;
import in.healthycoder.tnea.college.Community;

import java.util.*;

/**
 * Created by Suresh_Karuppannan on 5/22/2016.
 */
public class Cutoff {
    public static final String HEADERS = "DistCode,District,College Code,College Name,Branch,OC,BCM,BC,MBC,SCA,SC,ST";
    private String branch;
    private College college;
    private Map<Community, Double> marks;

    public Cutoff() {
        this.marks = new HashMap<>();
    }

    public Cutoff(College college, String branch) {
        this();
        Objects.requireNonNull(branch, "branch");
        Objects.requireNonNull(college, "college");

        this.branch = branch;
        this.college = college;
    }

    // 1,ARIYALUR,3016,UNIVERSITY COLLEGE OF ENGINEERING  ARIYALUR,ELECTRICAL AND ELECTRONICS ENGG.,178.25,163.75,173.25,175.5,171,171,0,
    public Cutoff(String csvString, Map<String, College> colleges) {
        this();
        String[] split = csvString.split(",");
        String[] cutoff = Arrays.copyOfRange(split, split.length - 7, split.length);

        this.college = colleges.get(split[2]);
        this.branch = split[4];
        setCutoff(cutoff);
    }

    public String getBranch() {
        return branch;
    }

    public Map<Community, Double> getMarks() {
        return marks;
    }

    public College getCollege() {
        return college;
    }

    public void setCutoff(List<Community> community, List<String> cutoff) {
        for (int i = 0; i < community.size(); i++) {
            setCutoff(community.get(i), cutoff.get(i));
        }
    }

    public void setCutoff(Community community, String cutoff) {
        String trimmed = cutoff.trim();
        double cutoffMark = 0.0;
        if (trimmed != null && !trimmed.equalsIgnoreCase("-")) {
            cutoffMark = Double.valueOf(trimmed);
        }
        this.marks.put(community, cutoffMark);
    }

    public void setCutoff(String[] marks) {
        this.marks.put(Community.OC, Double.valueOf(marks[0]));
        this.marks.put(Community.BCM, Double.valueOf(marks[1]));
        this.marks.put(Community.BC, Double.valueOf(marks[2]));
        this.marks.put(Community.MBC, Double.valueOf(marks[3]));
        this.marks.put(Community.SCA, Double.valueOf(marks[4]));
        this.marks.put(Community.SC, Double.valueOf(marks[5]));
        this.marks.put(Community.ST, Double.valueOf(marks[6]));
    }


    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                college.toString(),
                branch,
                marks.getOrDefault(Community.OC, 0.0),
                marks.getOrDefault(Community.BCM, 0.0),
                marks.getOrDefault(Community.BC, 0.0),
                marks.getOrDefault(Community.MBC, 0.0),
                marks.getOrDefault(Community.SCA, 0.0),
                marks.getOrDefault(Community.SC, 0.0),
                marks.getOrDefault(Community.ST, 0.0));
    }

    public static void main(String[] args) {
        String input = "1,ARIYALUR,3016,UNIVERSITY COLLEGE OF ENGINEERING  ARIYALUR,ELECTRICAL AND ELECTRONICS ENGG.,178.25,163.75,173.25,175.5,171,171,0,";
        String[] split = input.split(",");
        String[] marks = Arrays.copyOfRange(split, split.length - 7, split.length);
        System.out.println("Arrays.toString(marks) = " + Arrays.toString(marks));
    }

}
