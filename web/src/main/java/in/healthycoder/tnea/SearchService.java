package in.healthycoder.tnea;

import in.healthycoder.tnea.college.College;
import in.healthycoder.tnea.college.Community;

import java.util.stream.Stream;

/**
 * Created by Suresh_Karuppannan on 5/29/2016.
 */
public class SearchService {
    public Stream<College> search(CollegeSearchRequest search) {
        Community community = Community.getValue(search.getCommunity());
        System.out.println("community = " + community);
        System.out.println("CutoffManager.marks.size() = " + CutoffManager.marks.size());
        return CutoffManager.marks
                .stream()
                .filter(cutoff -> cutoff.getBranch().equalsIgnoreCase(search.getBranch()))
                .filter(cutoff ->
                        cutoff.getMarks().get(community) <= search.getCutoff() + 2 &&
                                cutoff.getMarks().get(community) >= search.getCutoff() - 2
                )
                .map(cutoff -> cutoff.getCollege());
    }

    ;
}
