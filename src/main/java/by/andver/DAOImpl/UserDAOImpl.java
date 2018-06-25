package by.andver.DAOImpl;

import by.andver.interfaces.UserDAO;
import by.andver.objects.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDAOImpl() {
    }

    public Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveUser(User user) {
        currentSession().saveOrUpdate(user);
    }

    public User findUserById(Integer id) {
        return currentSession().get(User.class,id);
    }

    public void deleteUser(User user) {
        currentSession().delete(user);
    }

    public void updateUser(User user) {
        currentSession().update(user);
    }
}
