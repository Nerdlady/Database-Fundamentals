package bank.system.repositories;

import bank.system.domain.BasicAccount;
import bank.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.MappedSuperclass;
import java.util.List;

@Repository
public interface BasicAccountRepository extends JpaRepository<BasicAccount,String> {

    List<BasicAccount> findAllByUser( User user);

    @Query("select b from BasicAccount as b where b.user = :user and b.class = :classType")
    List<BasicAccount> findAllByUserAndClass(@Param("user") User user,@Param("classType")String tClass);

}