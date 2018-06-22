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
    @ManyToOne(targetEntity = User.class)
    private User customer;
    @Column(nullable = false)
    private Integer firstPrice;
    @Column(nullable = false)
    private Integer complexityClass;
    @Column(nullable = false)
    private Date endDate;

    public Project() {
    }

    public Project(String name, User customer, Integer firstPrice, Integer complexityClass, Date endDate) {
        this.name = name;
        this.customer = customer;
        this.firstPrice = firstPrice;
        this.complexityClass = complexityClass;
        this.endDate = endDate;
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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", customer=" + customer +
                ", firstPrice=" + firstPrice +
                ", complexityClass=" + complexityClass +
                ", endDate=" + endDate +
                '}';
    }
}
