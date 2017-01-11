package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.User;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    List<User> findAllUsersByEmailLike(String provider);

    int countByPictureGreaterThan(byte[] size);

    List<User> findAllUsersByLastTimeLoggedInLessThan(Date date);
}
