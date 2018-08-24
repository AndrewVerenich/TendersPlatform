package by.andver.objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name="participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Tender tender;
    @NotNull
    @Positive(message = "Введите числовое значение больше нуля")
    @Column(nullable = false)
    private Integer bet;



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

}