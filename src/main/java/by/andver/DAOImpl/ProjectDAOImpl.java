package by.andver.DAOImpl;

import by.andver.interfaces.ProjectDAO;
import by.andver.objects.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ProjectDAOImpl implements ProjectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public ProjectDAOImpl() {
    }

    public Session currentSession(){
        try {
            return sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            return sessionFactory.openSession();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveProject(Project project) {
        currentSession().save(project);
    }

    public Project findProjectById(Integer id) {
        return currentSession().get(Project.class,id);
    }

    public void deleteProject(Project project) {
        currentSession().delete(project);
    }

    public void updateProject(Project project) {
        currentSession().update(project);
    }
}