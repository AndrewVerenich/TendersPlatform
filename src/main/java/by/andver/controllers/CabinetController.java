package by.andver.controllers;

import by.andver.interfaces.TenderService;
import by.andver.objects.Participant;
import by.andver.objects.Project;
import by.andver.objects.Tender;
import by.andver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "cabinet")
public class CabinetController {
    @Autowired
    public TenderService tenderService;

    @Autowired
    public PasswordEncoder encoder;

    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(method = RequestMethod.GET)
    public String cabinet(Model model, Principal principal){
        User user=tenderService.getUser(principal.getName());
        model.addAttribute("user",user);
        return "cabinet/myCabinet";
    }

    @RequestMapping(value = "myTenders", method = RequestMethod.GET)
    public String getMyTenders(Model model,Principal principal){
        model.addAttribute("tenders",tenderService.getUsersTenders(principal.getName()));
        return "cabinet/myTenders";
    }

    @RequestMapping(value = "myBets", method = RequestMethod.GET)
    public String getMyBets(Model model, Principal principal){
        List<Participant> bets;
        bets=tenderService.getMyBets(principal.getName());
        model.addAttribute("bets",bets);
        return "cabinet/myBets";
    }


    @RequestMapping(value = "editProfile",method = RequestMethod.GET)
    public String editProfile(Model model, Principal principal){
        User user=tenderService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "cabinet/editProfileForm";
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public String edit(@ModelAttribute String password, @ModelAttribute String name, @ModelAttribute String address,
                       @ModelAttribute String telNumber, @ModelAttribute String email, Model model, Principal principal){
        User user=tenderService.getUser(principal.getName());
        user.setPassword(encoder.encode(password));
        user.setName(name);
        user.setAddress(address);
        user.setTelNumber(telNumber);
        user.setEmail(email);
        tenderService.editUser(user);
        return "redirect:/cabinet";
    }

    @RequestMapping(value = "newTender", method = RequestMethod.GET)
    public String newTender(){
//        Tender tender=new Tender();
//        Project project=new Project();
//        model.addAttribute("tender",tender);
//        model.addAttribute("project",project);

        return "cabinet/tenderForm";
    }

    @RequestMapping(value = "createTender",method = RequestMethod.POST)
    public String createTender(@ModelAttribute ("name") String name,
                               @ModelAttribute ("complClass") Integer complClass,
                               @ModelAttribute ("price") Integer price,
                               @ModelAttribute ("dateP") String dateP,
                               @ModelAttribute ("dateT") String dateT,
                               Principal principal){
        Date dateEndProj=null;
        Date dateEndTend=null;
        try {
            dateEndProj=dateFormat.parse(dateP);
            dateEndTend=dateFormat.parse(dateT);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user=tenderService.getUser(principal.getName());
        Project project=new Project();
        project.setName(name);
        project.setComplexityClass(complClass);
        project.setFirstPrice(price);
        project.setEndDate(dateEndProj);
        project.setCustomer(user);
        tenderService.createNewProject(project);
        tenderService.createNewTender(project,dateEndTend);
        return "redirect:/cabinet";
    }
}
