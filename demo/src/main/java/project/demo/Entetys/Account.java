package project.demo.Entetys;

import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String UserName, UserPass, Phone, adress, FirstName, SecondName, NamepoBatke;

    public Account() {
    }

    public Account(int id, String userName, String userPass, String phone, String adress, String firstName, String secondName, String namepoBatke) {
        this.id = id;
        UserName = userName;
        UserPass = userPass;
        Phone = phone;
        this.adress = adress;
        FirstName = firstName;
        SecondName = secondName;
        NamepoBatke = namepoBatke;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPass() {
        return UserPass;
    }

    public void setUserPass(String userPass) {
        UserPass = userPass;
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
