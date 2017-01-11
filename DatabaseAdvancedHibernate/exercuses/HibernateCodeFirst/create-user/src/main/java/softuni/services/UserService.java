package softuni.services;

import softuni.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    void persist(User user);

    int getNumberOfUsersWithPictureGreaterThan(byte[] size);

    List<User> getUsersWithEmailProvider(String string);

    List<User> getUsersLastLoggedInBefore(Date date);
}
