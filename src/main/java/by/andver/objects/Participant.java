package by.andver.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @ManyToOne(targetEntity = Tender.class)
    private Tender tender;
    @Column(nullable = false)
    private Integer bet;
    @ManyToOne(targetEntity = Tender.class)
    private List<Tender> tenderList;
    @OneToOne(targetEntity = Tender.class)
    private List<Tender> winnTenderList;


    public Participant() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public List<Tender> getTenderList() {
        return tenderList;
    }

    public void setTenderList(List<Tender> tenderList) {
        this.tenderList = tenderList;
    }

    public List<Tender> getWinnTenderList() {
        return winnTenderList;
    }

    public void setWinnTenderList(List<Tender> winnTenderList) {
        this.winnTenderList = winnTenderList;
    }
}
