package by.andver.DAOImpl;

import by.andver.objects.Project;
import by.andver.objects.User;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class ProjectDAOImplTest {
    @Autowired
    public SessionFactory sessionFactory;
    @Autowired
    public UserDAOImpl userDAO;
    @Autowired
    public ProjectDAOImpl projectDAO;

    @Test
    public void shouldSaveProject(){
        User user1 = new User("user", "password", "Полесьежилстрой", "г. Брест, ул. Кижеватова, д. 60", "80162456987", "asasas", null);
        userDAO.saveUser(user1);
        Project project=new Project("Детский сад на 350 мест в г. Брест",user1,1000,2,new Date());
        projectDAO.saveProject(project);
        assertEquals(project.getName(),projectDAO.findProjectById(1).getName());
    }


}