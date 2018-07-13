package by.andver.controllers;

import by.andver.interfaces.TenderService;
import by.andver.interfaces.UserDAO;
import by.andver.objects.Tender;
import by.andver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    public TenderService tenderService;

    @Autowired
    public UserDAO userDAO;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getAllTenders(Model model){
        List<Tender> tenders=tenderService.getAllTenders();
        model.addAttribute("tenders",tenders);
        return "allTenders";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String registration(){
        return "registration";
    }

    @RequestMapping(value = "/cabinet",method = RequestMethod.GET)
    public String cabinet(){
        return "cabinet";
    }

    @RequestMapping(value = "/rules",method = RequestMethod.GET)
    public String rules(){
        return "rules";
    }
}

