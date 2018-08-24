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

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    public TenderService tenderService;

    @Autowired
    public PasswordEncoder encoder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:tenders?active=all";
    }

    @RequestMapping(value = "/tenders", method = RequestMethod.GET)
    public String getAllTenders(@RequestParam(defaultValue = "all") String active, @RequestParam(defaultValue = "1") Integer page,
                                Model model, Principal principal) {
        List<Tender> tenders;
        model.addAttribute("principal", principal);
        switch (active) {
            case "all":
                tenders = tenderService.getAllTenders(page);
                model.addAttribute("tenders", tenders);
                break;
            case "true":
                tenders = tenderService.getActiveTenders(page);
                model.addAttribute("tenders", tenders);
                break;
            case "false":
                tenders = tenderService.getCompletedTenders(page);
                model.addAttribute("tenders", tenders);
                break;
        }
        model.addAttribute("page", page);
        model.addAttribute("active", active);
        return "allTenders";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверный логин или пароль");
        }
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processingRegistration(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        tenderService.createNewUser(user);
        model.addAttribute("user", user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public String rules(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        return "rules";
    }
}

