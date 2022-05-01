package project.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import project.demo.items.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
