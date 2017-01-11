package softuni.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.UserAlbum;
import softuni.repositories.UserAlbumDao;
import softuni.services.UserAlbumService;

@Service
public class UserAlbumServiceImpl implements UserAlbumService {
    private final UserAlbumDao userAlbumDao;

    @Autowired
    public UserAlbumServiceImpl(UserAlbumDao userAlbumDao) {
        this.userAlbumDao = userAlbumDao;
    }

    @Override
    public void persist(UserAlbum userAlbum) {
        this.userAlbumDao.saveAndFlush(userAlbum);
    }
}
