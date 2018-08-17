package by.andver.DAOImpl;

import by.andver.interfaces.ParticipantDAO;
import by.andver.objects.Participant;
import by.andver.objects.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ParticipantDAOImpl implements ParticipantDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private static final String FIND_USERS_BETS="select p from Participant as p where p.user.username=?";


    public ParticipantDAOImpl() {
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

    public void saveParticipant(Participant participant) {
        currentSession().save(participant);
    }

    public Participant findParticipantById(Integer id) {
        return currentSession().get(Participant.class,id);
    }

    public void deleteParticipant(Participant participant) {
        currentSession().delete(participant);
    }

    public void updateParticipant(Participant participant) {
        currentSession().update(participant);
    }

    public List findUsersBets(String userName) {
        Query query=currentSession().createQuery(FIND_USERS_BETS);
        query.setParameter(0,userName);
        return query.getResultList();
    }
}
