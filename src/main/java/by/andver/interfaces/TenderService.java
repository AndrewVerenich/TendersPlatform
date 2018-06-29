package by.andver.interfaces;

import by.andver.objects.Project;
import by.andver.objects.Tender;
import by.andver.objects.User;

import java.util.Date;
import java.util.List;

public interface TenderService {
    User createNewUser(User user);
    Project createNewProject(Project project);
    Tender createNewTender(Project project, Date date);
    Boolean doBet(User user,Tender tender, Integer bet);
    void holdTenders();
    List getAllActiveTenders();
}
