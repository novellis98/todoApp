package com.example.primo.progetto.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class IndexController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String goToIndexPage(ModelMap model){
        model.put("name", getLoggedInUsername());
        return "index";
    }
    private String getLoggedInUsername(){
        Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
       return authentication.getName();

    }

}
