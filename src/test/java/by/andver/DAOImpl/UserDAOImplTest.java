package by.andver.DAOImpl;

import by.andver.interfaces.UserDAO;
import by.andver.objects.Project;
import by.andver.objects.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class UserDAOImplTest {
    @Autowired
    private UserDAO userDAO;

    private User user;

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
    }

    @Test
    public void shouldSaveUser(){
        userDAO.saveUser(user);
        Integer id=user.getId();
        assertEquals(user.getName(),userDAO.findUserById(id).getName());
        userDAO.deleteUser(user);
    }

    @Test
    public void shouldUpdateUser(){
        userDAO.saveUser(user);
        user.setName("Name");
        userDAO.updateUser(user);
        assertEquals(user.getName(),userDAO.findUserById(user.getId()).getName());
        userDAO.deleteUser(user);

    }

    @Test
    public void shouldDeleteUser(){
        userDAO.saveUser(user);
        Integer id=user.getId();
        userDAO.deleteUser(user);
        assertNull(userDAO.findUserById(id));
    }
}