package by.andver.controllers;

import by.andver.interfaces.ParticipantDAO;
import by.andver.interfaces.TenderService;
import by.andver.objects.Participant;
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

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TenderDetController {

    @Autowired
    public TenderService tenderService;

    @RequestMapping(value = "/tendDetails", method = RequestMethod.GET)
    public String tendDetails(@RequestParam Integer tenderId, Model model, Principal principal) {
        model.addAttribute("user", tenderService.getUser(principal.getName()));
        model.addAttribute("tender", tenderService.getTender(tenderId));
        return "tenderDetails/details";
    }

    @RequestMapping(value = "/doBet", method = RequestMethod.GET)
    public String doBet(@RequestParam Integer tenderId, Model model, Principal principal) {
        Tender tender = tenderService.getTender(tenderId);
        User user = tenderService.getUser(principal.getName());
        Participant participant = new Participant();
        model.addAttribute("user", user);
        model.addAttribute("tender", tender);
        model.addAttribute("participant", participant);
        return "tenderDetails/doBet";
    }

    @RequestMapping(value = "/doBet", method = RequestMethod.POST)
    public String bidAccepted(@Valid @ModelAttribute Participant participant, BindingResult result,
                              @RequestParam Integer tenderId, Principal principal, Model model) {
        Tender tender = tenderService.getTender(tenderId);
        User user = tenderService.getUser(principal.getName());
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("tender", tender);
            model.addAttribute("participant", participant);
            return "tenderDetails/doBet";
        }
        participant.setUser(user);
        participant.setTender(tender);
        tenderService.doBet(participant);
        return "redirect:/";
    }
}
