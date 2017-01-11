package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Long> {
}
