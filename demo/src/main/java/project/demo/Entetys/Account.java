package project.demo.Entetys;

import project.demo.Consts.Role;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String username;

    private String user_pass, Phone, adress, FirstName, SecondName, NamepoBatke;

    public Account() {
    }

    public Account(String username, String userpass, String phone, String adress, String firstName, String secondName, String namepoBatke) {

        this.username = username;
        user_pass = userpass;
        Phone = phone;
        this.adress = adress;
        FirstName = firstName;
        SecondName = secondName;
        NamepoBatke = namepoBatke;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public String getNamepoBatke() {
        return NamepoBatke;
    }

    public void setNamepoBatke(String namepoBatke) {
        NamepoBatke = namepoBatke;
    }
}
