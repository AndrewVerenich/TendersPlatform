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
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    public TenderService tenderService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getAllTenders(Model model){
        List<Tender> tenders=tenderService.getAllTenders();
        model.addAttribute("tenders",tenders);
        return "allTenders";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model){
        if (error!=null){
            model.addAttribute("error","Неверный логин или пароль");
        }
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

//    @RequestMapping(value = "/cabinet",method = RequestMethod.GET)
//    public String cabinet(Principal principal){
//        return "cabinet";
//    }

    @RequestMapping(value = "/rules",method = RequestMethod.GET)
    public String rules(){
        return "rules";
    }
}

