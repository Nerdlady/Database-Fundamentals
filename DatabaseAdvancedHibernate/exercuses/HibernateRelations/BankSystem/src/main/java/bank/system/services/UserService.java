package bank.system.services;

import bank.system.domain.User;

public interface UserService {
    void persist(User user);

    User getById(Long id);

    User getByUsername(String username);
}
