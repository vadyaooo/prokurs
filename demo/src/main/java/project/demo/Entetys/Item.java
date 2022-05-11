package project.demo.Entetys;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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


    public Item(String marka, String fullname, String imageurl, String typetech, String date, String detail, int howmany) {
        this.marka = marka;
        this.fullname = fullname;
        this.imageurl = imageurl;
        this.typetech = typetech;
        this.date = date;
        this.detail = detail;
        this.howmany = howmany;
    }

    @OneToMany(mappedBy = "item")
    private Set<Imeiitem> imeiitems;



    private String marka, fullname, imageurl, typetech, date, detail;
    private int howmany;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTypetech() {
        return typetech;
    }

    public void setTypetech(String typetech) {
        this.typetech = typetech;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
}
