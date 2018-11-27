package by.andver.DAOImpl;

import by.andver.interfaces.TenderDAO;
import by.andver.objects.Tender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextTestDAO.xml")
public class TenderDAOImplTest {
    @Autowired
    private TenderDAO tenderDAO;

    private Tender tender1;
    private Tender tender2;

    @Before
    public void init(){
        tender1=new Tender();
        tender1.setDateEndOfTender(new Date());
        tender1.setActive(true);

        tender2=new Tender();
        tender2.setDateEndOfTender(new Date());
        tender2.setActive(false);
    }

    @Test
    public void shouldSaveTender(){
        tenderDAO.saveTender(tender1);
        Integer id= tender1.getId();
        assertEquals(tender1.getActive(),tenderDAO.findTenderById(id).getActive());
        tenderDAO.deleteTender(tender1);
    }

    @Test
    public void shouldFindAllActiveTenders(){
        tenderDAO.saveTender(tender1);
        tenderDAO.saveTender(tender2);
        assertEquals(1,tenderDAO.findAllActiveTenders().toArray().length);
        tenderDAO.deleteTender(tender1);
        tenderDAO.deleteTender(tender2);
    }

}