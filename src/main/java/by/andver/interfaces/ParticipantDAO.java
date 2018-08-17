package by.andver.interfaces;

import by.andver.objects.Participant;
import by.andver.objects.User;

import java.util.List;

public interface ParticipantDAO {
    void saveParticipant(Participant participant);
    Participant findParticipantById(Integer id);
    void deleteParticipant(Participant participant);
    void updateParticipant (Participant participant);

    List findUsersBets(String userName);
}
