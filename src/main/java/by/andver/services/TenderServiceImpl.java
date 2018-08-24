package by.andver.services;

import by.andver.interfaces.*;
import by.andver.objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Authority authority=new Authority();
        authority.setRole(Role.ROLE_USER);
        authority.setUser(user);
        user.setAuthority(authority);
        userDAO.saveUser(user);
        return user;
    }

    public void removeUser(User user) {
        userDAO.deleteUser(user);
    }

    public User getUser(String username) {
        return userDAO.findUserByUserName(username);
    }

    public Project createNewProject(Project project) {
        projectDAO.saveProject(project);
        return project;
    }

    public void removeProject(Project project) {
        project.getCustomer().getProjectList().remove(project);
        userDAO.updateUser(project.getCustomer());
        projectDAO.deleteProject(project);
    }

    public Tender createNewTender(Tender tender, Project project, String username) {
        User user=getUser(username);
        user.getProjectList().add(project);
        project.setCustomer(user);
        tender.setProject(createNewProject(project));
        tender.setActive(true);
        tenderDAO.saveTender(tender);
        return tender;
    }


    public Tender getTender(Integer id) {
        return tenderDAO.findTenderById(id);
    }

    public void removeTender(Tender tender) {
        tenderDAO.deleteTender(tender);
    }


    public void doBet(Participant participant) {
        participantDAO.saveParticipant(participant);
    }

    @Scheduled(cron = "0 0 1 * * *",zone = "Europe/Minsk")
//    @Scheduled(cron = "*/10 * * * * *")
    public void holdTenders() {
//        System.out.println("in holdTenders()");
        Date currentDate=new Date();
        List tendersList=tenderDAO.findAllActiveTenders();
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
                tender.setActive(false);
                tenderDAO.updateTender(tender);
            }
        }
    }

    public List getActiveTenders(Integer page) {
        return tenderDAO.findActiveTenders(page);
    }

    public List getAllTenders(Integer page) {
        return tenderDAO.findAllTenders(page);
    }

    public List getCompletedTenders(Integer page) {
        return tenderDAO.findCompletedTenders(page);
    }

    public List getUsersTenders(String userName) {
        return tenderDAO.findTendersByCustomer(userName);
    }

    public List getMyBets(String userName) {
        return participantDAO.findUsersBets(userName);
    }

//    public void editUser(String userName) {
//
//        userDAO.updateUser(getUser(userName));
//    }

    public void editUser(String userName,String password, String name, String address, String telNumber, String email) {
        userDAO.updateUserByUsername(userName, password, name, address, telNumber, email);
    }



}
