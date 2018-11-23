package by.andver.interfaces;

import by.andver.objects.Participant;
import by.andver.objects.Project;
import by.andver.objects.Tender;
import by.andver.objects.User;

import java.util.List;

public interface TenderService extends FieldValueExists {
    User createNewUser(User user);
    void removeUser(String name);
    void removeUser(User user);
    User getUser(String username);
    Project createNewProject(Project project);
    void removeProject(Project project);
    Tender createNewTender(Tender tender, Project project, String username);
    Tender getTender(Integer id);
    void removeTender(Tender tender);
    void doBet(Participant participant);
    void holdTenders();
    List getActiveTenders(Integer page);
    List getAllTenders(Integer page);
    List getAllTendersWithoutPages();
    List getCompletedTenders(Integer page);
    List getUsersTenders(String userName);
    List getMyBets(String userName);
    void editUser(User user);
    List<Participant> getParticipantsByTendersId(Integer id);
}
