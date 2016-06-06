package in.healthycoder.tnea;

import in.healthycoder.tnea.college.Community;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Suresh_Karuppannan on 5/29/2016.
 */
public class SearchService {

    public CollegeSearchResponse search(CollegeSearchRequest search) {
        Community community = Community.getValue(search.getCommunity());
        System.out.println("community = " + community);
        System.out.println("CutoffManager.marks.size() = " + CutoffManager.marks.size());
        List<CollegeSearchResponse.FilteredCollege> colleges = CutoffManager.marks
                .stream()
                .filter(cutoff -> cutoff.getBranch().equalsIgnoreCase(search.getBranch()))
                .filter(cutoff ->
                        cutoff.getMarks().get(community) <= search.getCutoff() + 2 &&
                                cutoff.getMarks().get(community) >= search.getCutoff() - 2
                )
                .map(cutoff -> new CollegeSearchResponse.FilteredCollege(cutoff, community))
                .sorted()
                .collect(Collectors.toList());
        return new CollegeSearchResponse(colleges);
    }

    ;
}
