package project.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.Entetys.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
