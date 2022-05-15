package project.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.Entetys.ReturnConnect;

@Repository
public interface ReturnConnectRepository extends JpaRepository<ReturnConnect, Integer> {
}
