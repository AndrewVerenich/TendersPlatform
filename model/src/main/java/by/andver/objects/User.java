package by.andver.objects;
import by.andver.annotations.Unique;
import by.andver.interfaces.FieldValueExists;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Unique(message = "Пользователь с таким логином уже зарегистрирован",service = FieldValueExists.class)
    @NotNull(message = "Введите логин")
    @Size(min = 3, max = 20, message = "Логин должен быть от 3 до 20 символов")
    private String username;
    @NotNull(message = "Введите пароль")
    @Size(min = 3, message = "Пароль должен быть от 3 символов")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "Введите название организации")
    @Size(min = 3, max = 20, message = "Название должно быть от 3 до 30 символов")
    private String name;
    @Size(min=3,message = "Введите адресс")
    private String address;
    @Pattern(regexp = "^\\+?[0-9. ()-]{10,25}$",message = "Неверный номер телефона")
    private String telNumber;
    @Email(message = "Неверный email")
    private String email;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Project> projectList;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonIgnore
    private List<Participant> participants;
    @Column (nullable = false)
    private Boolean enabled=true;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
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