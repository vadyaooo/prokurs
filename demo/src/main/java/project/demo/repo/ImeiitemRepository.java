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
    List<Imeiitem> findDistinctByItem_Id(int id);
    List<Imeiitem> findByNalichiye(boolean e);
    List<Imeiitem> findDistinctByMarkaStartingWithAndFullnameStartingWithAndSerialnumberStartingWithAndIdStartingWith(String a, String b, String c, int d);
    List<Imeiitem> findByMarkaContainingOrSerialnumberContainingOrFullnameContainingAndNalichiye(String c, String a, String b, boolean r);
    //List<Imeiitem> findAllByMarkaContainingOrFullnameContainingOrSerialnumberContainingOrId(String a);

}
