package by.andver.services;

import by.andver.interfaces.ProjectDAO;
import by.andver.interfaces.TenderDAO;
import by.andver.interfaces.TenderService;
import by.andver.interfaces.UserDAO;
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

    private User user;
    private User user1;
    private Project project;
    private Tender tender;
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

        project=new Project();
        project.setName("Детский сад на 350 мест в г. Брест");
        project.setCustomer(user);
        project.setFirstPrice(1000);
        project.setComplexityClass(2);
        project.setEndDate(new Date());

        user.getProjectList().add(project);

//        tender=new Tender();
//        tender.setProject(project);
//        tender.setActive(true);
//        tender.setDateEndOfTender(new Date());
//        tender.setParticipantList(new LinkedList<Participant>());

    }

    @Test
    public void shouldCreateNewUser(){
        tenderService.createNewUser(user);
        Integer id=user.getId();
        assertEquals(user.getName(),userDAO.findUserById(id).getName());
        userDAO.deleteUser(user);
    }

    @Test
    public void shouldCreateNewProject(){
        tenderService.createNewUser(user);
        assertEquals(projectDAO.findProjectById(project.getId()).getCustomer().getName(),
                userDAO.findUserById(user.getId()).getName());
        userDAO.deleteUser(user);
    }

    @Test
    public void shouldDoBet(){
//        userDAO.saveUser(user);
//        userDAO.saveUser(user1);
//        projectDAO.saveProject(project);
//        tenderDAO.saveTender(tender);
//        tenderService.doBet(user1,tender,999);
//
//        assertEquals(new Integer(999),tender.getParticipantList().get(0).getBet());
//        tender.setProject(null);
//        tender.setParticipantList(null);
//        tenderDAO.deleteTender(tender);
//        userDAO.updateUser(user);
//        userDAO.updateUser(user1);

    }

    @Test
    public void shouldHoldTenders() {
//        while (true){}
    }

}