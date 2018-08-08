package by.andver.interfaces;

import by.andver.objects.Tender;
import by.andver.objects.User;

import java.util.List;

public interface TenderDAO {
    void saveTender(Tender tender);
    Tender findTenderById(Integer id);
    void deleteTender(Tender tender);
    void updateTender(Tender tender);
    List findActiveTenders();
    List findAllTenders();
    List findTendersByCustomer(User user);
    List findCompletedTenders();
}
