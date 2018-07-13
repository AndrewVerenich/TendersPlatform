package by.andver.services;

import by.andver.interfaces.*;
import by.andver.objects.Participant;
import by.andver.objects.Project;
import by.andver.objects.Tender;
import by.andver.objects.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.LinkedList;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class TenderServiceImplTest {
    @Autowired
    public TenderService tenderService;
    @Autowired
    public UserDAO userDAO;
    @Autowired
    public ProjectDAO projectDAO;
    @Autowired
    public TenderDAO tenderDAO;
    @Autowired
    public ParticipantDAO participantDAO;

    private User user;
    private User user1;
    private User user2;
    private Project project;
    private Project project1;
    private Tender tender;
    private Tender tender1;
    @Before
    public void init(){
        user=new User();
        user.setLogin("user");
        user.setPassword("password");
        user.setName("Полесьежилстрой");
        user.setAddress("г. Брест, ул. Кижеватова, д. 60");
        user.setTelNumber("80162456987");
        user.setEmail("pzs@mail.by");
        user.setProjectList(new LinkedList<Project>());
        user.setParticipantList(new LinkedList<Project>());

        user1=new User();
        user1.setLogin("user1");
        user1.setPassword("password1");
        user1.setName("Стройтрест 8");
        user1.setAddress("г. Брест, ул. Бульвар Шевченко, д. 8");
        user1.setTelNumber("80162456987");
        user1.setEmail("strt8@mail.by");
        user1.setProjectList(new LinkedList<Project>());

        user2=new User();
        user2.setLogin("user2");
        user2.setPassword("password2");
        user2.setName("Брестжилстрой");
        user2.setAddress("г. Брест, ул. Высокая, д. 15");
        user2.setTelNumber("80162456987");
        user2.setEmail("bzhstr8@mail.by");
        user2.setProjectList(new LinkedList<Project>());

        project=new Project();
        project.setName("Детский сад на 350 мест в г. Брест");
        project.setCustomer(user);
        project.setFirstPrice(1000);
        project.setComplexityClass(2);
        project.setEndDate(new Date());

        project1=new Project();
        project1.setName("Автовокзал в г. Брест");
        project1.setCustomer(user2);
        project1.setFirstPrice(1200);
        project1.setComplexityClass(2);
        project1.setEndDate(new Date());

        user.getProjectList().add(project);
        user2.getProjectList().add(project1);
    }

    @Test
    public void shouldCreateNewUser(){
        tenderService.createNewUser(user);
        Integer id=user.getId();
        assertEquals(user.getName(),userDAO.findUserById(id).getName());
        tenderService.removeUser(user);
    }

    @Test
    public void shouldCreateNewProject(){
        tenderService.createNewUser(user);
        assertEquals(projectDAO.findProjectById(project.getId()).getCustomer().getName(),
                userDAO.findUserById(user.getId()).getName());
        tenderService.removeUser(user);
    }

    @Test
    public void shouldCreateNewTender(){
        tenderService.createNewUser(user);
        Tender tender=tenderService.createNewTender(project,new Date());
        assertEquals(tender.getProject().getName(),tenderDAO.findTenderById(tender.getId()).getProject().getName());
        tenderService.removeTender(tender);
        tenderService.removeUser(user);
    }

    @Test
    public void shouldDoBet(){
        tenderService.createNewUser(user);
        tenderService.createNewUser(user1);
        Tender tender=tenderService.createNewTender(project,new Date());
        tenderService.doBet(user1,tender,999);
        assertEquals(new Integer(999),
                tenderDAO.findTenderById(tender.getId())
                        .getParticipantList().get(0).getBet());
        tenderService.removeTender(tender);
        tenderService.removeUser(user);
        tenderService.removeUser(user1);
    }

    @Test
    public void shouldHoldTenders() {
        tenderService.createNewUser(user);
        tenderService.createNewUser(user1);
        tenderService.createNewUser(user2);
        Tender tender=tenderService.createNewTender(project,new Date());
        tenderService.doBet(user1,tender,999);
        tenderService.doBet(user2,tender,998);
        tenderService.holdTenders();
//        tender=tenderDAO.refreshTender(tender);
//        assertEquals(tender.getWinner().getBet(),
//                new Integer(998));
//
        assertEquals(tenderDAO.findTenderById(tender.getId()).getWinner().getBet(),
                new Integer(998));
        tender.setWinner(null);
        tenderDAO.updateTender(tender);
        tenderService.removeTender(tender);
        tenderService.removeUser(user);
        tenderService.removeUser(user1);
        tenderService.removeUser(user2);
    }
    @Test
    public void insertData(){
        tenderService.createNewUser(user);
        tenderService.createNewUser(user1);
        tenderService.createNewUser(user2);
        Tender tender=tenderService.createNewTender(project,new Date());
        Tender tender1=tenderService.createNewTender(project1,new Date());
    }

}