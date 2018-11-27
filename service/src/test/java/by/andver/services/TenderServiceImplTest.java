package by.andver.services;

import by.andver.interfaces.*;
import by.andver.objects.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.LinkedList;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextTestService.xml")
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
    @Autowired
    public PasswordEncoder encoder;

    private User user;
    private User user1;
    private User user2;
    private User user3;
    private Project project;
    private Project project1;
    private Tender tender;
    private Tender tender1;
    @Before
    public void init(){
        user=new User();
        user.setUsername("user");
        user.setPassword(encoder.encode("user"));
        user.setName("Полесьежилстрой");
        user.setAddress("г. Брест, ул. Кижеватова, д. 60");
        user.setTelNumber("80162456987");
        user.setEmail("pzs@mail.by");
        user.setEnabled(true);
        user.setProjectList(new LinkedList<Project>());
        user.setParticipants(new LinkedList<Participant>());

        user1=new User();
        user1.setUsername("user1");
        user1.setPassword(encoder.encode("user1"));
        user1.setName("Стройтрест 8");
        user1.setAddress("г. Брест, ул. Бульвар Шевченко, д. 8");
        user1.setTelNumber("80162456987");
        user1.setEmail("strt8@mail.by");
        user1.setEnabled(true);
        user1.setProjectList(new LinkedList<Project>());

        user2=new User();
        user2.setUsername("user2");
        user2.setPassword(encoder.encode("user2"));
        user2.setName("Брестжилстрой");
        user2.setAddress("г. Брест, ул. Высокая, д. 15");
        user2.setTelNumber("80162456987");
        user2.setEmail("bzhstr8@mail.by");        user2.setEnabled(true);
        user2.setEnabled(true);
        user2.setProjectList(new LinkedList<Project>());

        user3=new User();
        user3.setUsername("1");
        user3.setPassword(encoder.encode("1"));
        user3.setName("1");
        user3.setAddress("1");
        user3.setTelNumber("1");
        user3.setEmail("1");
        user3.setEnabled(true);
        user3.setProjectList(new LinkedList<Project>());

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

        user2.getProjectList().add(project1);

        tender=new Tender();

    }

    @Test
    public void shouldCreateNewUser(){
        tenderService.createNewUser(user);
        Integer id=user.getId();
        assertEquals(user.getUsername(),userDAO.findUserById(id).getUsername());
        tenderService.removeUser(user);
    }

    @Test
    public void shouldRemoveUser(){
        tenderService.createNewUser(user);
        Integer id=user.getId();
        tenderService.removeUser(user);
        Assert.assertNull(userDAO.findUserById(id));
    }


    @Test
    public void shouldCreateNewProject(){
        user.getProjectList().add(project);
        tenderService.createNewUser(user);
        tenderService.createNewProject(project);
        Integer id=project.getId();
        assertEquals(project.getName(),projectDAO.findProjectById(id).getName());
        tenderService.removeUser(user);
    }

    @Test
    public void shouldCreateNewTender(){
        tenderService.createNewUser(user);
        tender.setDateEndOfTender(new Date());
        tenderService.createNewTender(tender,project,user.getUsername());
        Integer id=tender.getId();
        assertEquals(tender.getProject().getCustomer().getUsername(),
                tenderDAO.findTenderById(id).getProject().getCustomer().getUsername());
        tenderService.removeTender(tender);
        tenderService.removeUser(user);
    }

    @Test
    public void shouldDoBet(){
        tenderService.createNewUser(user);
        tenderService.createNewUser(user1);
        tender.setDateEndOfTender(new Date());
        tenderService.createNewTender(tender,project,user.getUsername());
        Participant participant=new Participant();
        participant.setUser(user1);
        participant.setTender(tender);
        participant.setBet(999);
        tenderService.doBet(participant);
        assertEquals(new Integer(999),
                tenderDAO.findTenderById(tender.getId())
                        .getParticipantList().get(0).getBet());
        participantDAO.deleteParticipant(participant);
        tenderService.removeTender(tender);
        tenderService.removeUser(user);
        tenderService.removeUser(user1);
    }

    @Test
    public void shouldHoldTenders() {
        tenderService.createNewUser(user);
        tenderService.createNewUser(user1);
        tenderService.createNewUser(user2);
        tender.setDateEndOfTender(new Date());
        tenderService.createNewTender(tender,project,user.getUsername());
        Participant participant1=new Participant();
        participant1.setUser(user1);
        participant1.setTender(tender);
        participant1.setBet(999);
        tenderService.doBet(participant1);

        Participant participant2=new Participant();
        participant2.setUser(user2);
        participant2.setTender(tender);
        participant2.setBet(998);
        tenderService.doBet(participant2);

        tenderService.holdTenders();

        assertEquals(tenderDAO.findTenderById(tender.getId()).getWinner().getUser().getUsername(),
                user2.getUsername());
        tender.setWinner(null);
        tenderDAO.updateTender(tender);
        participantDAO.deleteParticipant(participant1);
        participantDAO.deleteParticipant(participant2);
        tenderService.removeTender(tender);
        tenderService.removeUser(user);
        tenderService.removeUser(user1);
        tenderService.removeUser(user2);
    }
}