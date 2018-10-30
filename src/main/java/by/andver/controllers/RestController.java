package by.andver.controllers;

import by.andver.interfaces.TenderService;
import by.andver.objects.Tender;
import by.andver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "rest")
public class RestController {

    @Autowired
    private TenderService tenderService;

    @GetMapping(value = "users/{name}")
    public User getUser (@PathVariable String name){
        User user=tenderService.getUser(name);
        return user;
    }

    @GetMapping(value = "tenders/{id}")
    public Tender getTender(@PathVariable Integer id){
        return tenderService.getTender(id);
    }

    @GetMapping(value = "tenders")
    public List<Tender> getTenders(){
        return tenderService.getAllTendersWithoutPages();
    }
}
