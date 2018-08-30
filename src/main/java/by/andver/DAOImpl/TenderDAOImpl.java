package by.andver.DAOImpl;

import by.andver.interfaces.TenderDAO;
import by.andver.objects.Tender;
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
public class TenderDAOImpl implements TenderDAO {
    private static final String FIND_ACTIVE_TENDERS="select t FROM Tender as t WHERE t.active=true";
    private static final String FIND_COMPLETED_TENDERS="select t FROM Tender as t WHERE t.active=false";
    private static final String FIND_ALL_TENDERS="FROM Tender";
    private static final String FIND_TENDERS_BY_CUSTOMER="select t from Tender as t where t.project.customer.username=?";

    private static final Integer MAX_TENDERS_PER_PAGE=5;


    private final SessionFactory sessionFactory;

    @Autowired
    public TenderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        try {
            return sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            return sessionFactory.openSession();
        }
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

    public List findActiveTenders(Integer page) {
        Query query=currentSession().createQuery(FIND_ACTIVE_TENDERS);
        query.setMaxResults(MAX_TENDERS_PER_PAGE);
        query.setFirstResult((page-1)*MAX_TENDERS_PER_PAGE);
        return query.getResultList();
    }

    public List findAllTenders(Integer page) {
        Query query=currentSession().createQuery(FIND_ALL_TENDERS);
        query.setMaxResults(MAX_TENDERS_PER_PAGE);
        query.setFirstResult((page-1)*MAX_TENDERS_PER_PAGE);
        return query.getResultList();
    }

    public List findTendersByCustomer(String userName) {
        Query query=currentSession().createQuery(FIND_TENDERS_BY_CUSTOMER);
        query.setParameter(0,userName);
        return query.getResultList();
    }
    public List findCompletedTenders(Integer page) {
        Query query=currentSession().createQuery(FIND_COMPLETED_TENDERS);
        query.setMaxResults(MAX_TENDERS_PER_PAGE);
        query.setFirstResult((page-1)*MAX_TENDERS_PER_PAGE);
        return query.getResultList();
    }

    public List findAllActiveTenders() {
        return currentSession().createQuery(FIND_ACTIVE_TENDERS).getResultList();
    }




}
