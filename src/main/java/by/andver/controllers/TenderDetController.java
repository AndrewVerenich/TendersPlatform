package by.andver.controllers;

import by.andver.interfaces.TenderService;
import by.andver.objects.Tender;
import by.andver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class TenderDetController {

    @Autowired
    public TenderService tenderService;

    @RequestMapping(value = "/tendDetails", method = RequestMethod.GET)
    public String tendDetails(@RequestParam Integer tenderId, Model model){
        model.addAttribute("tender",tenderService.getTender(tenderId));
        return "tenderDetails/details";
    }

    @RequestMapping(value = "/doBet",method = RequestMethod.GET)
    public String doBet(@RequestParam Integer tenderId,Model model,Principal principal){
        model.addAttribute("tender",tenderService.getTender(tenderId));
        User user=tenderService.getUser(principal.getName());
        model.addAttribute("user",user);
        return "tenderDetails/doBet";
    }

    @RequestMapping(value = "/bidAccepted", method = RequestMethod.POST)
    public String bidAccepted (@ModelAttribute("bid") Integer bid, @RequestParam Integer tenderId,
                               BindingResult result, Model model, Principal principal){

        //        --------------------------------------обработка BindingResult
        Tender tender=tenderService.getTender(tenderId);
        User user=tenderService.getUser(principal.getName());
        tenderService.doBet(user,tender,bid);
        return "redirect:/";
    }
}
