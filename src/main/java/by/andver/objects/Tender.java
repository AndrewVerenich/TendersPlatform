package by.andver.objects;

import java.util.Date;
import java.util.List;

public class Tender {
    private Integer id;
    private Project project;
    private List<Participant> participantList;
    private Date dateEndOfTender;
    private Boolean active;
    private User winner;

    public Tender() {
    }

    public Tender(Project project, List<Participant> participantList, Date dateEndOfTender, Boolean active, User winner) {
        this.project = project;
        this.participantList = participantList;
        this.dateEndOfTender = dateEndOfTender;
        this.active = active;
        this.winner = winner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<Participant> participantList) {
        this.participantList = participantList;
    }

    public Date getDateEndOfTender() {
        return dateEndOfTender;
    }

    public void setDateEndOfTender(Date dateEndOfTender) {
        this.dateEndOfTender = dateEndOfTender;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Tender{" +
                "id=" + id +
                ", project=" + project +
                ", participantList=" + participantList +
                ", dateEndOfTender=" + dateEndOfTender +
                ", active=" + active +
                '}';
    }
}
