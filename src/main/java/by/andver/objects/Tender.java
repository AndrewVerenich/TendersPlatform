package by.andver.objects;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "tenders")
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinTable(name = "tender_project",
            joinColumns = @JoinColumn(name = "tender_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Project project;
    @ManyToMany
    @JoinTable(name = "tender_participant",
            joinColumns = @JoinColumn(name = "tender_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<Participant> participantList;
    @Column(nullable = false)
    private Date dateEndOfTender;
    @Column(nullable = false)
    private Boolean active;
    @OneToOne
    @JoinTable(name = "tender_winner",
            joinColumns = @JoinColumn(name = "tender_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private Participant winner;

    public Tender() {
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

    public Participant getWinner() {
        return winner;
    }

    public void setWinner(Participant winner) {
        this.winner = winner;
    }
}
