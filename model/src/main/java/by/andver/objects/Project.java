package by.andver.objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "projects")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 5,message = "Введите название проекта")
    private String name;
    @ManyToOne
    @JoinColumn
    private User customer;
    @NotNull(message = "Введите стоимость")
    @Positive(message = "Введите числовое значение больше нуля")
    private Integer firstPrice;
    @NotNull(message = "Введите класс сложности")
    @Positive(message = "Введите числовое значение больше нуля")
    private Integer complexityClass;
    @FutureOrPresent(message = "Вы ввели неверную дату")
    @NotNull(message = "Выберите дату")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToOne(mappedBy = "project")
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