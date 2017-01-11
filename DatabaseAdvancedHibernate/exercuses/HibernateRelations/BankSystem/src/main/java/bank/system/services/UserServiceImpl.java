package bank.system.services;

import bank.system.domain.User;
import bank.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void persist(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User getByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }
}
