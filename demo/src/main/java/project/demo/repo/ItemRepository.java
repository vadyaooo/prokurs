package project.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.demo.Entetys.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
}
