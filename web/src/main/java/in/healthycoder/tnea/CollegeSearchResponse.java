package in.healthycoder.tnea;

import in.healthycoder.tnea.college.College;
import in.healthycoder.tnea.college.Community;
import in.healthycoder.tnea.cutoff.Cutoff;

import java.util.List;

/**
 * Created by Suresh_Karuppannan on 6/5/2016.
 */
public class CollegeSearchResponse {
    private List<FilteredCollege> colleges;
    private String message;

    public CollegeSearchResponse(List<FilteredCollege> colleges) {
        this.colleges = colleges;
    }

    public static class FilteredCollege extends College implements Comparable<FilteredCollege> {
        private double cutoff;

        public FilteredCollege(Cutoff cutoff, Community community) {
            super(cutoff.getCollege());
            this.cutoff = cutoff.getMarks().get(community);
        }

        public double getCutoff() {
            return cutoff;
        }

        @Override
        public int compareTo(FilteredCollege college) {
            return (int) ((college.cutoff - this.cutoff) * 100);
        }
    }

    public List<FilteredCollege> getColleges() {
        return colleges;
    }

    public String getMessage() {
        return message;
    }
}
