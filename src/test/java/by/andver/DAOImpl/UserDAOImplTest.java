package by.andver.DAOImpl;

import by.andver.interfaces.ProjectDAO;
import by.andver.interfaces.UserDAO;
import by.andver.objects.Project;
import by.andver.objects.User;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class UserDAOImplTest {
    @Autowired
    public SessionFactory sessionFactory;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ProjectDAO projectDAO;

    User user;
    Project project;

    @Before
    public void init(){
//        user = new User("user", "password", "Полесьежилстрой", "г. Брест, ул. Кижеватова, д. 60", "80162456987", "asasas", new LinkedList<Project>());
//        project=new Project("Детский сад на 350 мест в г. Брест",null,1000,3,new Date());
    }

    @Test
    public void shouldSaveUser(){
        userDAO.saveUser(user);
        Integer id=user.getId();
        assertEquals(user.getName(),userDAO.findUserById(id).getName());
        userDAO.deleteUser(user);
    }
    @Test
    public void shouldSaveUsersProject(){
        project.setCustomer(user);
        user.getProjectList().add(project);
        userDAO.saveUser(user);
        Integer id=user.getId();
        assertNotNull(userDAO.findUserById(id).getProjectList().get(0));


        user.getProjectList().remove(project);
        userDAO.updateUser(user);
    }
}