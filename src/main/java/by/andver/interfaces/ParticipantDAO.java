package by.andver.interfaces;

import by.andver.objects.Participant;

public interface ParticipantDAO {
    void saveParticipant(Participant participant);
    Participant findParticipantById(Integer id);
    void deleteParticipant(Participant participant);
    void updateParticipant (Participant participant);
}
