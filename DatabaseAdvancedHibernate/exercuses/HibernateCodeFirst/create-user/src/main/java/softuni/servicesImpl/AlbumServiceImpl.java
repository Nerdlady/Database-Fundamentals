package softuni.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Album;
import softuni.repositories.AlbumDao;
import softuni.services.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {
    public final AlbumDao albumDao;

    @Autowired
    public AlbumServiceImpl(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    @Override
    public void persist(Album album) {
        this.albumDao.saveAndFlush(album);
    }
}
