package by.andver.interfaces;

import by.andver.objects.Tender;

public interface TenderDAO {
    void saveTender(Tender tender);
    Tender findTenderById(Integer id);
    void deleteTender(Tender tender);
    void updateTender(Tender tender);
}
