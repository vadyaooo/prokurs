package project.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.demo.Entetys.Historyedit;
import project.demo.Entetys.Imeiitem;

import java.util.List;

@Repository
public interface HistoryeditRepository extends JpaRepository<Historyedit, Integer> {
    List<Historyedit> findDistinctByImeiitem_Id(int id);
    List<Historyedit> findDistinctByItem_Id(int id);
    //List<Historyedit> findByImeiitem_SerialnumberAndItem_Id(String a, int b);
    List<Historyedit> findDistinctByImeiitem_SerialnumberAndItem_Id(String a, int b);
    List<Historyedit> findByImeiitem_SerialnumberStartingWithAndItem_Id(String a, int b);
    List<Historyedit> findByImeiitem_SerialnumberContainingOrImeiitem_MarkaContainingOrImeiitem_FullnameContaining(String a, String b, String c);
    List<Historyedit> findByImeiitem_SerialnumberContainingAndNameuserofedit(String c, String d);
    List<Historyedit> findByImeiitem_SerialnumberOrImeiitem_FullnameOrImeiitem_MarkaOrNameuserofeditContaining(String a, String b, String c, String d);
    List<Historyedit> findByNameuserofedit(String a);
}
