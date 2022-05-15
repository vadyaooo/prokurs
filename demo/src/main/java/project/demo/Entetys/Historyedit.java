package project.demo.Entetys;

import javax.persistence.*;

@Entity
public class Historyedit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "imeiitemid")
    private Imeiitem imeiitem;

    @ManyToOne
    @JoinColumn(name = "itemid")
    private Item item;

    String dataofedit, nameuserofedit, typeofedit;


    public Historyedit() {
    }

    public Historyedit(String dataofedit, String nameuserofedit, String typeofedit) {
        this.dataofedit = dataofedit;
        this.nameuserofedit = nameuserofedit;
        this.typeofedit = typeofedit;
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

    public Imeiitem getImeiitem() {
        return imeiitem;
    }

    public void setImeiitem(Imeiitem imeiitem) {
        this.imeiitem = imeiitem;
    }

    public String getDataofedit() {
        return dataofedit;
    }

    public void setDataofedit(String dataofedit) {
        this.dataofedit = dataofedit;
    }

    public String getNameuserofedit() {
        return nameuserofedit;
    }

    public void setNameuserofedit(String nameuserofedit) {
        this.nameuserofedit = nameuserofedit;
    }

    public String getTypeofedit() {
        return typeofedit;
    }

    public void setTypeofedit(String typeofedit) {
        this.typeofedit = typeofedit;
    }
}
