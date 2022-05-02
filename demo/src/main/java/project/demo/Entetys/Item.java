package project.demo.Entetys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public Item() {
    }

    public Item(String marka, String fullname, int howmany) {
        this.marka = marka;
        this.fullname = fullname;
        this.howmany = howmany;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getHowmany() {
        return howmany;
    }

    public void setHowmany(int howmany) {
        this.howmany = howmany;
    }

    private String marka, fullname;
    private int howmany;
}
