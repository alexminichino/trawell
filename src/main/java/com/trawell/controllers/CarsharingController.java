package com.trawell.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.trawell.models.Carsharing;
import com.trawell.models.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * @author Milione Vincent
 * 
 * maps all the users requests to the corresponding html page
 */

@Controller
@RequestMapping("carsharing")
public class CarsharingController {
    
    /**
     * @author Milione Vincent
     * The method handles "/carsharing/create" get request and maps it to the corresponding page.
     * If user is not logged, unlogged user gets sent to error page
     * @param session
     * @return url of the page used to create a car sharing ad
     */
    @GetMapping("/create")
    public String create(HttpSession session) {
        return session.getAttribute("user") == null ? "pages/error" : "pages/carsharing/createcarsharing";
    }

    /**
     * The method handles "/carsharing/change?id = {id}" get request and maps it to the corresponding page
     * The url is invoked when user wants to change a specific carsharing ad that he owns.
     * If user is not logged, unlogged user gets sent to error page
     * @author Milione Vincent
     * @param session
     * @param id id of the corresponding car sharing that user wants to update
     * @param model 
     * @return url of the page used to modify the content of a carsharing ad
     */
    @GetMapping("/change")
    public String change(HttpSession session, @RequestParam("id") Long id, Model model){

        User user = (User) session.getAttribute("user");

        if (user == null ? false : user.getUserCreatedAdList() == null ? false : user.getUserCreatedAdList().size() > 0) {
            int index = user.getUserCreatedAdList().indexOf(new Carsharing(id));
            model.addAttribute("carsharing", user.getUserCreatedAdList().get(index));
        }

        return "pages/carsharing/modifycarsharing";
    }

    /**
     * The method handles "/carsharing/view?id = {id}" get request and maps it to the corresponding page.
     * The url is invoked when user wants to view the contents of a specific carsharing ad that he owns.
     * If user is not logged, unlogged user gets sent to error page
     * @author Milione Vincent
     * @param session
     * @param id id of the carsharing the user wants to view
     * @param model
     * @return url of the view used to view the content of a carsharing ad
     */
    @GetMapping("/view")
    public String view(HttpSession session, @RequestParam("id") Long id, Model model) {
        
        User user = (User) session.getAttribute("user");
        Carsharing carsharing = new Carsharing(id);

        if (user == null ? false : user.getUserCreatedAdList() == null ? false : user.getUserCreatedAdList().size() > 0) {
            int index = user.getUserCreatedAdList().indexOf(carsharing);
            carsharing.setDescription(carsharing.getDescription() == null ? "" : carsharing.getDescription());
            model.addAttribute("carsharing", user.getUserCreatedAdList().get(index));
        }
        
        return "pages/carsharing/viewcarsharing"; 
    }

    /**
     * The method handles "/carsharing/list-view" get request and maps it to the corresponding page.
     * The url is invoked when user wants to view a generic list of all the carsharing ads he owns.
     * If user is not logged, unlogged user gets sent to error page.
     * @author Milione Vincent
     * @param session
     * @param model
     * @return url of the view used to view displaying the list
     */
    @GetMapping("/list-view")
    public String list(HttpSession session, Model model) {
        
        User user = (User) session.getAttribute("user");
        List<Carsharing> list = user.getUserCreatedAdList();

        if (list == null ? false : list.size() < 0) {
            
            model.addAttribute("isEmpty", true);
        } else {
            model.addAttribute("carsharingAds", user.getUserCreatedAdList());
            model.addAttribute("isEmpty", false);
        }

        return "pages/carsharing/list-view";
    }
}