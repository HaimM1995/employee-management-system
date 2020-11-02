package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Company;

@Repository
public interface CompanyRepositry extends JpaRepository<Company, Integer> {
}
