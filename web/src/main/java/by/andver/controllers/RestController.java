package by.andver.controllers;

import by.andver.exceptions.BadRequestException;
import by.andver.exceptions.ResourceNotFoundException;
import by.andver.interfaces.TenderService;
import by.andver.objects.Participant;
import by.andver.objects.Tender;
import by.andver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "rest")
public class RestController {

    private final TenderService tenderService;

    @Autowired
    public RestController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @GetMapping(value = "users/{name}")
    public User getUser (@PathVariable String name){
        User user=tenderService.getUser(name);
        if (user==null){
            throw new ResourceNotFoundException();
        }
        return user;
    }

    @PostMapping(value = "users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()){
            throw new BadRequestException();
        }
        tenderService.createNewUser(user);
        return user;
    }

    @DeleteMapping(value = "users/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String name){
        tenderService.removeUser(name);
    }

    @GetMapping(value = "tenders/{id}")
    public Tender getTender(@PathVariable Integer id){
        Tender tender=tenderService.getTender(id);
        if (tender==null){
            throw new ResourceNotFoundException();
        }
        return tender;
    }

    @GetMapping(value = "tenders")
    public List<Tender> getTenders(){
        List<Tender> list=tenderService.getAllTendersWithoutPages();
        if (list.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return list;
    }

    @GetMapping(value = "tenders/{id}/participants")
    public List<Participant> getParticipantsByTendersId(@PathVariable Integer id){
        List<Participant> list=tenderService.getParticipantsByTendersId(id);
        if (list.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return list;
    }
}
