package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String searchHandler(Model model,
                                @RequestParam(name="searchType", required = false) String searchType,
                                @RequestParam(name="searchTerm", required = false) String searchTerm){
        model.addAttribute("columns", ListController.columnChoices);
        if(ListController.columnChoices.containsKey(searchType)){
            //Valid searchType passed by GET request

            //Search Type All
            if (searchType.equals("all")){
                model.addAttribute("jobs",JobData.findByValue(searchTerm));

             return "search";
            }

            //Search Type Not All
            model.addAttribute("jobs",JobData.findByColumnAndValue(searchType,searchTerm));
            return "search";
        }
        else{
            return "redirect:search";
        }
        //return searchType + " - " + searchTerm;
    }
}
