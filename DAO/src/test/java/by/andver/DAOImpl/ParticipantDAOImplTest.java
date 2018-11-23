package by.andver.DAOImpl;

import by.andver.interfaces.ParticipantDAO;
import by.andver.objects.Participant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextDAO.xml")
public class ParticipantDAOImplTest {
    @Autowired
    private ParticipantDAO participantDAO;

    private Participant participant;
    @Before
    public void init(){
        participant=new Participant();
        participant.setBet(100);
    }
    @Test
    public void shouldSaveParticipant() {
        participantDAO.saveParticipant(participant);
        Integer id=participant.getId();
        assertEquals(participant.getBet(),participantDAO.findParticipantById(id).getBet());
        participantDAO.deleteParticipant(participant);
    }
    @Test
    public void shouldDeleteParticipant() {
        participantDAO.saveParticipant(participant);
        Integer id= participant.getId();
        participantDAO.deleteParticipant(participant);
        Assert.assertNull(participantDAO.findParticipantById(id));
    }

    @Test
    public void shouldUpdateParticipant() {
        participantDAO.saveParticipant(participant);
        participant.setBet(1);
        participantDAO.updateParticipant(participant);
        assertEquals(participant.getBet(),participantDAO.findParticipantById(participant.getId()).getBet());
        participantDAO.deleteParticipant(participant);
    }

}