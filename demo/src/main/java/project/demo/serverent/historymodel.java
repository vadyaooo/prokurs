package project.demo.serverent;

import project.demo.Entetys.Historyedit;
import project.demo.Entetys.Imeiitem;
import project.demo.Entetys.Item;

import javax.persistence.*;

public class historymodel {
    Imeiitem imeiitem;
    Historyedit historyedit;

    public historymodel() {
    }

    public historymodel(Imeiitem imeiitem, Historyedit historyedit) {
        this.imeiitem = imeiitem;
        this.historyedit = historyedit;
    }

    public Imeiitem getImeiitem() {
        return imeiitem;
    }

    public void setImeiitem(Imeiitem imeiitem) {
        this.imeiitem = imeiitem;
    }

    public Historyedit getHistoryedit() {
        return historyedit;
    }

    public void setHistoryedit(Historyedit historyedit) {
        this.historyedit = historyedit;
    }
}
