package by.andver.interfaces;

import by.andver.objects.Project;
import by.andver.objects.Tender;
import by.andver.objects.User;

import java.util.Date;
import java.util.List;

public interface TenderService {
    User createNewUser(User user);
    void removeUser(User user);
    User getUser(String username);
    Project createNewProject(Project project);
    void removeProject(Project project);
    Tender createNewTender(Project project, Date date);
    Tender getTender(Integer id);
    void removeTender(Tender tender);
    Boolean doBet(User user,Tender tender, Integer bet);
    void holdTenders();
    List getActiveTenders(Integer page);
    List getAllTenders(Integer page);
    List getCompletedTenders(Integer page);
    List getUsersTenders(String userName);
    List getMyBets(String userName);
    void editUser(User user);

}
