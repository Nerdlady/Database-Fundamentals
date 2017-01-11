package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {
}
