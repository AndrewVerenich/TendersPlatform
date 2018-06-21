package by.andver.objects;

import java.util.Date;

public class Project {
    private Integer id;
    private String name;
    private User customer;
    private Integer firstPrice;
    private Integer complexityClass;
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
                ", customer=" + customer +
                ", firstPrice=" + firstPrice +
                ", complexityClass=" + complexityClass +
                ", endDate=" + endDate +
                '}';
    }
}
