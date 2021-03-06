package by.andver.controllers;

import by.andver.interfaces.TenderService;
import by.andver.objects.Participant;
import by.andver.objects.Project;
import by.andver.objects.Tender;
import by.andver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "cabinet")
public class CabinetController {
    private final TenderService tenderService;

    private final PasswordEncoder encoder;

    @Autowired
    public CabinetController(TenderService tenderService, PasswordEncoder encoder) {
        this.tenderService = tenderService;
        this.encoder = encoder;
    }

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String cabinet(Model model, Principal principal) {
        User user = tenderService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "cabinet/myCabinet";
    }

    @RequestMapping(value = "myTenders", method = RequestMethod.GET)
    public String getMyTenders(Model model, Principal principal) {
        model.addAttribute("tenders", tenderService.getUsersTenders(principal.getName()));
        return "cabinet/myTenders";
    }

    @RequestMapping(value = "myBets", method = RequestMethod.GET)
    public String getMyBets(Model model, Principal principal) {
        List<Participant> bets;
        bets = tenderService.getMyBets(principal.getName());
        model.addAttribute("bets", bets);
        return "cabinet/myBets";
    }


    @RequestMapping(value = "editProfile", method = RequestMethod.GET)
    public String editProfile(Model model, Principal principal) {
        User user = tenderService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "cabinet/editProfileForm";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()){
            return "cabinet/editProfileForm";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        tenderService.editUser(user);
        return "redirect:/cabinet";
    }

    @RequestMapping(value = "newTender", method = RequestMethod.GET)
    public String newTender(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("tender", new Tender());
        return "cabinet/tenderForm";
    }

    @RequestMapping(value = "newTender", method = RequestMethod.POST)
    public String createTender(@Valid @ModelAttribute Project project, BindingResult projResult,
                               @Valid @ModelAttribute Tender tender, BindingResult tendResult,
                               Principal principal) {
        if (projResult.hasErrors()) {
            return "cabinet/tenderForm";
        }
        if (tendResult.hasErrors()) {
            return "cabinet/tenderForm";
        }
        tenderService.createNewTender(tender,project,principal.getName());
        return "redirect:/cabinet";
    }
}
