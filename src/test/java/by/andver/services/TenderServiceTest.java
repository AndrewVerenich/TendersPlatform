package by.andver.services;

import by.andver.interfaces.ProjectDAO;
import by.andver.interfaces.UserDAO;
import by.andver.objects.Project;
import by.andver.objects.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.LinkedList;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class TenderServiceTest {
    @Autowired
    public TenderService tenderService;
    @Autowired
    public UserDAO userDAO;
    @Autowired
    public ProjectDAO projectDAO;

    User user;
    Project project;
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

        project=new Project();
        project.setName("Детский сад на 350 мест в г. Брест");
        project.setCustomer(user);
        project.setFirstPrice(1000);
        project.setComplexityClass(2);
        project.setEndDate(new Date());

        user.getProjectList().add(project);



    }

    @Test
    public void shouldCreateNewTender() throws Exception {
        userDAO.saveUser(user);
        projectDAO.saveProject(project);
        tenderService.createNewTender(project,new Date());

    }

}