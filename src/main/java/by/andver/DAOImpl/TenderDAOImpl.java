package by.andver.DAOImpl;

import by.andver.interfaces.TenderDAO;
import by.andver.objects.Tender;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class TenderDAOImpl implements TenderDAO {
    private static final String FIND_ALL_ACTIVE_TENDERS="select t FROM Tender as t WHERE t.active=true";
    private static final String FIND_ALL_TENDERS="FROM Tender";


    @Autowired
    private SessionFactory sessionFactory;

    public TenderDAOImpl() {
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

    public void saveTender(Tender tender) {
        currentSession().save(tender);
    }

    public Tender findTenderById(Integer id) {
        return currentSession().get(Tender.class,id);
    }

    public void deleteTender(Tender tender) {
        currentSession().delete(tender);
    }

    public void updateTender(Tender tender) {
        currentSession().update(tender);
    }

    public List findActiveTenders() {
        return currentSession().createQuery(FIND_ALL_ACTIVE_TENDERS).getResultList();
    }

    public List findAllTenders() {
        return currentSession().createQuery(FIND_ALL_TENDERS).getResultList();
    }

}
