package by.andver.controllers;

import by.andver.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    public TenderService tenderService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "index";
    }
}
