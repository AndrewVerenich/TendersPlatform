package by.andver.DAOImpl;

import by.andver.interfaces.ParticipantDAO;
import by.andver.objects.Participant;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ParticipantDAOImpl implements ParticipantDAO {
    private final SessionFactory sessionFactory;

    private static final String FIND_USERS_BETS="select p from Participant as p where p.user.username=?";


    @Autowired
    public ParticipantDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        try {
            return sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            return sessionFactory.openSession();
        }
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
