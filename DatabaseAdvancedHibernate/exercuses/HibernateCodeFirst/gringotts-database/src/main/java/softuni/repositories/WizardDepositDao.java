package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.WizardDeposit;

@Repository
public interface WizardDepositDao extends JpaRepository<WizardDeposit,Long>{
}
