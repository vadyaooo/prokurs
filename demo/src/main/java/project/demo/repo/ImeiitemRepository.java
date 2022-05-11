package project.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.demo.Entetys.Imeiitem;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImeiitemRepository extends JpaRepository<Imeiitem, Integer> {
    List<Imeiitem> findDistinctByItem_IdAndNalichiye(int id, boolean e);

}
