package by.andver.objects;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
//    @ManyToOne
    @ManyToOne
    @JoinColumn
    private User customer;
    @Column(nullable = false)
    private Integer firstPrice;
    @Column(nullable = false)
    private Integer complexityClass;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;
//    @OneToOne(targetEntity = Tender.class)
    @OneToOne(mappedBy = "project",cascade = CascadeType.ALL)
    private Tender tender;

    public Project() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Integer getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Integer firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Integer getComplexityClass() {
        return complexityClass;
    }

    public void setComplexityClass(Integer complexityClass) {
        this.complexityClass = complexityClass;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }
}
