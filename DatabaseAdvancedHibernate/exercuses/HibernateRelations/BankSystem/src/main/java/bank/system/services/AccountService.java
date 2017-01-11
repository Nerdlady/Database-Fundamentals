package bank.system.services;

import bank.system.domain.BasicAccount;
import bank.system.domain.User;

import java.util.List;

public interface AccountService {
    void persist(BasicAccount account);

    BasicAccount getById(String id);

    List<BasicAccount> getAllWithUser(User usere);

    List<BasicAccount> getAllWithUser(User usere, String clazz);
}
