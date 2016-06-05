package in.healthycoder.tnea;

import in.healthycoder.tnea.college.College;
import in.healthycoder.tnea.college.Community;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Suresh_Karuppannan on 5/29/2016.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelMap model) {
        model.addAttribute("communities", Community.values());
        model.addAttribute("branches", Branch.LIST);
        return new ModelAndView("index", "command", new CollegeSearch(null, 5, "COMPUTER SCIENCE AND ENGG."));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute CollegeSearch collegeSearch, ModelMap model) {
        System.out.println("collegeSearch = " + collegeSearch);

        model.addAttribute("communities", Community.values());
        model.addAttribute("branches", Branch.LIST);

        SearchService searchService = new SearchService();
        List<College> colleges = searchService.search(collegeSearch).collect(Collectors.toList());
        model.addAttribute("colleges", colleges);

        return new ModelAndView("index", "command", collegeSearch);
    }

}
