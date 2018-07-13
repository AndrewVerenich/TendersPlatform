package by.andver.interfaces;

import by.andver.objects.Project;
import by.andver.objects.Tender;
import by.andver.objects.User;

import java.util.Date;
import java.util.List;

public interface TenderService {
    User createNewUser(User user);
    void removeUser(User user);
    Project createNewProject(Project project);
    void removeProject(Project project);
    Tender createNewTender(Project project, Date date);
    void removeTender(Tender tender);
    Boolean doBet(User user,Tender tender, Integer bet);
    void holdTenders();
    List getAllActiveTenders();
    List getAllTenders();
}
