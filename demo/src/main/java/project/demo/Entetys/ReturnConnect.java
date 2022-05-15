package project.demo.Entetys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReturnConnect {
    @Id
    @GeneratedValue
    private int id;

    private String name, theme, email, textofsms;

    public ReturnConnect() {
    }

    public ReturnConnect(String name, String theme, String email, String textofsms) {
        this.name = name;
        this.theme = theme;
        this.email = email;
        this.textofsms = textofsms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTextofsms() {
        return textofsms;
    }

    public void setTextofsms(String textofsms) {
        this.textofsms = textofsms;
    }
}
