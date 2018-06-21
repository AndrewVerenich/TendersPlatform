package by.andver.objects;

public class Participant {
    private Integer id;
    private User user;
    private Tender tender;
    private Integer bet;

    public Participant() {
    }

    public Participant(User user, Tender tender, Integer bet) {
        this.user = user;
        this.tender = tender;
        this.bet = bet;
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

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", user=" + user +
                ", bet=" + bet +
                '}';
    }
}
