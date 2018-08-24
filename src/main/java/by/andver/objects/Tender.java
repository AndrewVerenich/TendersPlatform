package by.andver.objects;

import com.sun.istack.internal.Interned;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "tenders")
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Project project;
    @OneToMany(mappedBy = "tender",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Participant> participantList;
    @Future(message = "Вы ввели неверную дату")
    @NotNull(message = "Выберите дату")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateEndOfTender;
    @Column(nullable = false)
    private Boolean active;
    @OneToOne
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