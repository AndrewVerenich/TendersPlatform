package by.andver.interfaces;

import by.andver.objects.Tender;
import java.util.List;

public interface TenderDAO {
    void saveTender(Tender tender);
    Tender findTenderById(Integer id);
    void deleteTender(Tender tender);
    void updateTender(Tender tender);
    List findActiveTenders(Integer page);
    List findAllTenders(Integer page);
    List findTendersByCustomer(String userName);
    List findCompletedTenders(Integer page);

    List findAllActiveTenders();


}
