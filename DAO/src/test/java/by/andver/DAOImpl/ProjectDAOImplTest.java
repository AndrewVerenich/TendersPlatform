package by.andver.DAOImpl;

import by.andver.interfaces.ProjectDAO;
import by.andver.objects.Project;
import org.junit.Assert;
import org.junit.Before;
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
    private ProjectDAO projectDAO;

    private Project project;



    @Before
    public void init(){
        project=new Project();
        project.setName("Школа в г. Жабинка");
        project.setFirstPrice(1000);
        project.setComplexityClass(2);
        project.setEndDate(new Date());
    }

    @Test
    public void shouldSaveProject(){
        projectDAO.saveProject(project);
        Integer id=project.getId();
        assertEquals(project.getName(),projectDAO.findProjectById(id).getName());
        projectDAO.deleteProject(project);
    }

    @Test
    public void shouldUpdateProject(){
        projectDAO.saveProject(project);
        Integer id=project.getId();
        project.setFirstPrice(500);
        projectDAO.updateProject(project);
        assertEquals(project.getFirstPrice(),
                projectDAO.findProjectById(id).getFirstPrice());
        projectDAO.deleteProject(project);
    }

    @Test
    public void shouldDeleteProject(){
        projectDAO.saveProject(project);
        Integer id=project.getId();
        projectDAO.deleteProject(project);
        Assert.assertNull(projectDAO.findProjectById(id));

    }
}