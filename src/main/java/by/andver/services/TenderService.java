package by.andver.services;

import by.andver.interfaces.ParticipantDAO;
import by.andver.interfaces.ProjectDAO;
import by.andver.interfaces.TenderDAO;
import by.andver.interfaces.UserDAO;
import by.andver.objects.Participant;
import by.andver.objects.Project;
import by.andver.objects.Tender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;

@Service
public class TenderService {
    @Autowired
    private ParticipantDAO participantDAO;
    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private TenderDAO tenderDAO;
    @Autowired
    private UserDAO userDAO;

    public Tender createNewTender(Project project, Date date){
        Tender tender=new Tender();
        tender.setProject(project);
        tender.setParticipantList(new LinkedList<Participant>());
        tender.setActive(true);
        tender.setDateEndOfTender(date);
        tenderDAO.saveTender(tender);
        return tender;
    }
}
