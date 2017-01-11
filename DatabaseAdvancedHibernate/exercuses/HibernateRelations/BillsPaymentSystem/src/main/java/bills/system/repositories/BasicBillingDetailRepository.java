package bills.system.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bills.system.domain.BasicBillingDetail;

@Repository
public interface BasicBillingDetailRepository extends JpaRepository<BasicBillingDetail,String> {
}