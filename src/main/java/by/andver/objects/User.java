package by.andver.objects;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Введите логин")
    @Size(min = 3, max = 20, message = "Логин должен быть от 3 до 20 символов")
    @Column(nullable = false)
    private String username;
    @NotNull(message = "Введите пароль")
    @Size(min = 3, message = "Пароль должен быть от 3 символов")
    @Column(nullable = false)
    private String password;
    @NotNull(message = "Введите название организации")
    @Size(min = 3, max = 20, message = "Название должно быть от 3 до 30 символов")
    @Column(nullable = false)
    private String name;
    @Size(min=3,message = "Введите адресс")
    @Column(nullable = false)
    private String address;
    @Pattern(regexp = "^\\+?[0-9. ()-]{10,25}$",message = "Неверный номер телефона")
    @Column(nullable = false)
    private String telNumber;
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",message = "Неверный email")
    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Project> projectList;
    @OneToMany(mappedBy = "user",cascade =CascadeType.REMOVE)
    private List<Participant> participants;

    @Column (nullable = false)
    private Boolean enabled=true;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Authority authority;

    public User() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}