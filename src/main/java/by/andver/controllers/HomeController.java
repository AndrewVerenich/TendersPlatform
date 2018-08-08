package by.andver.controllers;

import by.andver.interfaces.TenderService;
import by.andver.objects.Tender;
import by.andver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

//    private final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    public TenderService tenderService;

    @Autowired
    public PasswordEncoder encoder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "redirect:tenders?active=all";
    }

    @RequestMapping(value = "/tenders",method = RequestMethod.GET)
    public String getAllTenders(@RequestParam String active, Model model, Principal principal){
        List<Tender> tenders;
        model.addAttribute("principal",principal);
        switch (active){
            case "all":
                tenders=tenderService.getAllTenders();
                model.addAttribute("tenders",tenders);
                break;
            case "true":
                tenders=tenderService.getActiveTenders();
                model.addAttribute("tenders",tenders);
                break;
            case "false":
                tenders=tenderService.getCompletedTenders();
                model.addAttribute("tenders",tenders);
                break;
        }
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
    public String registration(Model model){
        User user=new User();
        model.addAttribute("userForm",user);
        return "registration";
    }

    @RequestMapping(value = "/cabinet",method = RequestMethod.GET)
    public String cabinet(Model model,Principal principal){
        User user=tenderService.getUser(principal.getName());
        model.addAttribute("user",user);
        return "cabinet/myCabinet";
    }

    @RequestMapping(value = "/rules",method = RequestMethod.GET)
    public String rules(){
        return "rules";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userForm") User user, BindingResult result, Model model){


//        --------------------------------------обработка BindingResult
        String password=user.getPassword();
        user.setPassword(encoder.encode(password));
        tenderService.createNewUser(user);
        model.addAttribute("user",user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/tendDetails", method = RequestMethod.GET)
    public String tendDetails(@RequestParam Integer tenderId, Model model){
        model.addAttribute("tender",tenderService.getTender(tenderId));
        return "details";
    }

    @RequestMapping(value = "/doBet",method = RequestMethod.GET)
    public String doBet(@RequestParam Integer tenderId,Model model,Principal principal){
        model.addAttribute("tender",tenderService.getTender(tenderId));
        User user=tenderService.getUser(principal.getName());
        model.addAttribute("user",user);
        return "doBet";
    }

    @RequestMapping(value = "/bidAccepted", method = RequestMethod.POST)
    public String bidAccepted (@ModelAttribute("bid") Integer bid,@RequestParam Integer tenderId,
                               BindingResult result,Model model, Principal principal){

        //        --------------------------------------обработка BindingResult
        Tender tender=tenderService.getTender(tenderId);
        User user=tenderService.getUser(principal.getName());
        tenderService.doBet(user,tender,bid);
        return "bidAccepted";
    }

    @RequestMapping(value = "cabinet/myTenders", method = RequestMethod.GET)
    public String grtMyTenders(Model model,Principal principal){
        User user=tenderService.getUser(principal.getName());
        model.addAttribute("tenders",tenderService.getUsersTenders(user));
        return "cabinet/myTenders";
    }
}

