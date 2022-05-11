package project.demo.Entetys;

import javax.persistence.*;
import java.util.List;

@Entity
public class Imeiitem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "itemid")
    private Item item;


    String marka, fullname, serialnumber,
    year, dataofadd, nameuserofadd;

    boolean nalichiye;


    public Imeiitem() {
    }

    public Imeiitem(String marka,
                    String fullname, String serialnumber,
                    String year, String dataofadd,
                    String nameuserofadd, boolean nalichiye) {
        this.marka = marka;
        this.fullname = fullname;
        this.serialnumber = serialnumber;
        this.year = year;
        this.dataofadd = dataofadd;
        this.nameuserofadd = nameuserofadd;
        this.nalichiye = nalichiye;
    }


    public boolean isNalichiye() {
        return nalichiye;
    }

    public void setNalichiye(boolean nalichiye) {
        this.nalichiye = nalichiye;
    }

    public int getId() {
        return id;
    }




    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDataofadd() {
        return dataofadd;
    }

    public void setDataofadd(String dataofadd) {
        this.dataofadd = dataofadd;
    }

    public String getNameuserofadd() {
        return nameuserofadd;
    }

    public void setNameuserofadd(String nameuserofadd) {
        this.nameuserofadd = nameuserofadd;
    }
}
