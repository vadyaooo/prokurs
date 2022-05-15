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

    private String user_pass, phone, adress, first_name, second_name, namepo_batke;

    public Account() {
    }

    public Account(String username, String userpass, String phone, String adress, String firstName, String secondName, String namepoBatke) {

        this.username = username;
        user_pass = userpass;
        phone = phone;
        this.adress = adress;
        first_name = firstName;
        second_name = secondName;
        namepo_batke = namepoBatke;
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
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getNamepo_batke() {
        return namepo_batke;
    }

    public void setNamepo_batke(String namepo_batke) {
        this.namepo_batke = namepo_batke;
    }
}
