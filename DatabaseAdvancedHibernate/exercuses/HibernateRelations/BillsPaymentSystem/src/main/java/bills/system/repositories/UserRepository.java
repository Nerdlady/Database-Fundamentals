package bills.system.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bills.system.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}