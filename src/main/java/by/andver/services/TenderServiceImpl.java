package by.andver.services;

import by.andver.interfaces.*;
import by.andver.objects.Participant;
import by.andver.objects.Project;
import by.andver.objects.Tender;
import by.andver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class TenderServiceImpl implements TenderService {
    @Autowired
    private ParticipantDAO participantDAO;
    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private TenderDAO tenderDAO;
    @Autowired
    private UserDAO userDAO;

    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

    public User createNewUser(User user){
        userDAO.saveUser(user);
        return user;
    }

    public Project createNewProject(Project project) {
        projectDAO.saveProject(project);
        return project;
    }

    public Tender createNewTender(Project project, Date date){
        Tender tender=new Tender();
        tender.setProject(project);
        tender.setParticipantList(new LinkedList<Participant>());
        tender.setActive(true);
        tender.setDateEndOfTender(date);
        tenderDAO.saveTender(tender);
        return tender;
    }

    public Boolean doBet(User user, Tender tender, Integer bet) {
        if (!tender.getProject().getCustomer().getName().equals(user.getName())){
        Participant participant=new Participant();
        participant.setUser(user);
        participant.setBet(bet);
        participant.setTender(tender);
        participantDAO.saveParticipant(participant);
        tender.getParticipantList().add(participant);
        tenderDAO.updateTender(tender);
        return true;
        }
        return false;
    }

    @Scheduled(cron = "0 0 1 * * *",zone = "Europe/Minsk")
//    @Scheduled(cron = "*/10 * * * * *")
    public void holdTenders() {
//        System.out.println("in holdTenders()");
        Date currentDate=new Date();
        List tendersList=tenderDAO.findActiveTenders();
        for (Object aTendersList : tendersList) {
            Tender tender = (Tender) aTendersList;
            if (dateFormat.format(currentDate).
                    equals(dateFormat.format(tender.getDateEndOfTender()))){
                List<Participant> participantList=tender.getParticipantList();
                Participant winner=null;
                for (Participant participant: participantList) {
                    if (winner==null) {
                        winner=participant;
                    }
                    if (participant.getBet()<winner.getBet()){
                        winner=participant;
                    }
                    tender.setWinner(winner);
                }
            }
        }
    }

    public List getAllActiveTenders() {
        return tenderDAO.findActiveTenders();
    }

}
