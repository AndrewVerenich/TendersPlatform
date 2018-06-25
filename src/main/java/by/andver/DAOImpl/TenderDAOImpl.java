package by.andver.DAOImpl;

import by.andver.interfaces.TenderDAO;
import by.andver.objects.Tender;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class TenderDAOImpl implements TenderDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public TenderDAOImpl() {
    }
    public Session currentSession(){
        return sessionFactory.getCurrentSession();
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

}
