package by.andver.DAOImpl;

import by.andver.interfaces.UserDAO;
import by.andver.objects.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;


@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    private static final String FIND_USER_BY_USERNAME="select u FROM User as u WHERE u.username=?";
    private static final String UPDATE_USER_BY_USERNAME="update User u set u.name=?, u.password=?, u.address=?, u.telNumber=?, u.email=? WHERE u.username=?";
    private static final String COUNT_USERS_BY_USERNAME="select count(u) from User u where u.username=?";

    private final SessionFactory sessionFactory;
    private static final Logger logger=Logger.getLogger(UserDAOImpl.class);


    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        try {
            return sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            return sessionFactory.openSession();
        }
    }

    public void saveUser(User user) {
        currentSession().save(user);
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

    public void updateUserByUsername(String userName,String password, String name, String address, String telNumber, String email) {
        Query query=currentSession().createQuery(UPDATE_USER_BY_USERNAME);
        query.setParameter(0,name);
        query.setParameter(1,password);
        query.setParameter(2,address);
        query.setParameter(3,telNumber);
        query.setParameter(4,email);
        query.setParameter(5,userName);
        query.executeUpdate();
    }


    public User findUserByUserName(String username) {
        Query query=currentSession().createQuery(FIND_USER_BY_USERNAME);
        query.setParameter(0,username);
        try {
            User user=(User) query.getSingleResult();
            return (User) query.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    public boolean existsByUserName(String username) {
        Query query=currentSession().createQuery(COUNT_USERS_BY_USERNAME);
        query.setParameter(0,username);
        Long count= (Long) query.getSingleResult();
        logger.info("Rows with username="+username+"-"+count);
        if (count==0){
            return false;
        }else
            return true;
    }
}
