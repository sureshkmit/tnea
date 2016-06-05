package in.healthycoder.tnea;

/**
 * Created by Suresh_Karuppannan on 5/29/2016.
 */
public class CollegeSearch {
    private Double cutoff;
    private int community;
    private String branch;

    public CollegeSearch() {
    }

    public CollegeSearch(Double cutoff, int community, String branch) {
        this.cutoff = cutoff;
        this.community = community;
        this.branch = branch;
    }

    public Double getCutoff() {
        return cutoff;
    }

    public void setCutoff(double cutoff) {
        this.cutoff = cutoff;
    }

    public int getCommunity() {
        return community;
    }

    public void setCommunity(int community) {
        this.community = community;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", cutoff, community, branch);
    }
}
