package project.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.demo.Entetys.Historyedit;
import project.demo.Entetys.Imeiitem;

@Repository
public interface HistoryeditRepository extends CrudRepository<Historyedit, Integer> {
}
