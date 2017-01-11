package bills.system.services;

import bills.system.domain.User;
import bills.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void persist(User user) {
        this.userRepository.saveAndFlush(user);
    }
}
