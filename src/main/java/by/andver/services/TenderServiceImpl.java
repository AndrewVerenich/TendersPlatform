package by.andver.services;

import by.andver.interfaces.*;
import by.andver.objects.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class TenderServiceImpl implements TenderService{

    private static final Logger logger=Logger.getLogger(TenderServiceImpl.class);

    private final ParticipantDAO participantDAO;
    private final ProjectDAO projectDAO;
    private final TenderDAO tenderDAO;
    private final UserDAO userDAO;

    @Autowired
    public TenderServiceImpl(ParticipantDAO participantDAO, ProjectDAO projectDAO, TenderDAO tenderDAO, UserDAO userDAO) {
        this.participantDAO = participantDAO;
        this.projectDAO = projectDAO;
        this.tenderDAO = tenderDAO;
        this.userDAO = userDAO;
    }

    public User createNewUser(User user){
        logger.info("Create user with username= "+ user.getUsername());
        Authority authority=new Authority();
        authority.setRole(Role.ROLE_USER);
        authority.setUser(user);
        user.setAuthority(authority);
        userDAO.saveUser(user);
        return user;
    }

    public void removeUser(User user) {
        logger.info("Remove user with username= "+ user.getUsername());
        userDAO.deleteUser(user);
    }

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public User getUser(String username) {
        logger.info("Get user with username= "+ username);
        return userDAO.findUserByUserName(username);
    }

    public Project createNewProject(Project project) {
        logger.info("Create new project  with name= "+ project.getName());
        projectDAO.saveProject(project);
        return project;
    }

    public void removeProject(Project project) {
        logger.info("Remove new project with name= "+ project.getName());
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
        logger.info("Create new tender, projectName= "+ project.getName()+", customer="+username);
        tenderDAO.saveTender(tender);
        return tender;
    }


    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public Tender getTender(Integer id) {
        logger.info("Get Tender by id= "+id);
        return tenderDAO.findTenderById(id);
    }

    public void removeTender(Tender tender) {
        tenderDAO.deleteTender(tender);
    }


    public void doBet(Participant participant) {
        logger.info("User "+participant.getUser().getUsername()+", bet "+participant.getBet()+" on tender id="+participant.getTender().getId());
        participantDAO.saveParticipant(participant);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.SERIALIZABLE)
    @Scheduled(cron = "0 0 1 * * *",zone = "Europe/Minsk")
//    @Scheduled(cron = "*/10 * * * * *")
    public void holdTenders() {
        logger.info("hold tenders");
        Date currentDate=new Date();
        List tendersList=tenderDAO.findAllActiveTenders();
        for (Object aTendersList : tendersList) {
            Tender tender = (Tender) aTendersList;
            if (currentDate.compareTo(tender.getDateEndOfTender())>-1){
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
                logger.info("Tender id="+tender.getId()+" winner="+(winner!=null ? winner.getUser().getUsername() : " none"));
                tender.setActive(false);
                tenderDAO.updateTender(tender);
            }
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List getActiveTenders(Integer page) {
        logger.info("Get active tenders");
        return tenderDAO.findActiveTenders(page);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List getAllTenders(Integer page) {
        logger.info("Get all tenders");
        return tenderDAO.findAllTenders(page);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List getCompletedTenders(Integer page) {
        logger.info("Get completed tenders");
        return tenderDAO.findCompletedTenders(page);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List getUsersTenders(String userName) {
        logger.info("Get users ("+userName+") tenders");
        return tenderDAO.findTendersByCustomer(userName);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List getMyBets(String userName) {
        logger.info("Get users ("+userName+") bets");
        return participantDAO.findUsersBets(userName);
    }

    public void editUser(User user) {
        userDAO.updateUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean fieldValueExists(Object value) throws UnsupportedOperationException {
        return this.userDAO.existsByUserName(value.toString());
    }
}
