package softuni.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.User;
import softuni.repositories.UserDao;
import softuni.services.UserService;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void persist(User user) {
        this.userDao.saveAndFlush(user);
    }

    @Override
    public int getNumberOfUsersWithPictureGreaterThan(byte[] size) {
        return userDao.countByPictureGreaterThan(size);
    }

    @Override
    public List<User> getUsersWithEmailProvider(String email) {
        return this.userDao.findAllUsersByEmailLike("%" + email);
    }

    @Override
    public List<User> getUsersLastLoggedInBefore(Date date) {
        return this.userDao.findAllUsersByLastTimeLoggedInLessThan(date);
    }


}
