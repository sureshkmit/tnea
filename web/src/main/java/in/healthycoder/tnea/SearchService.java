package in.healthycoder.tnea;

import in.healthycoder.tnea.college.Community;

import java.util.Arrays;
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
                .map(cutoff -> new CollegeSearchResponse.FilteredCollege(cutoff, community))
                .sorted() // desc
                .collect(Collectors.toList());
        if (colleges.size() <= 10) {
            return new CollegeSearchResponse(colleges);
        } else if (search.getCutoff() >= colleges.get(0).getCutoff()) {
            colleges = colleges.subList(0, Math.min(colleges.size(), 10));
        } else if (search.getCutoff() < colleges.get(colleges.size() - 1).getCutoff()) {
            colleges = colleges.subList(Math.max(colleges.size() - 10, 0), colleges.size());
        } else {
            colleges = colleges.stream().filter(college ->
                    college.getCutoff() <= search.getCutoff() + 3 &&
                            college.getCutoff() >= search.getCutoff() - 3
            ).collect(Collectors.toList());
        }
        return new CollegeSearchResponse(colleges);
    }
}
