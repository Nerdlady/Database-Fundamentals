package softuni.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Picture;
import softuni.repositories.PictureDao;
import softuni.services.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureDao pictureDao;

    @Autowired
    public PictureServiceImpl(PictureDao pictureDao) {
        this.pictureDao = pictureDao;
    }

    @Override
    public void persist(Picture picture) {
        this.pictureDao.saveAndFlush(picture);
    }

    @Override
    public void save(Picture picture) {
        this.pictureDao.save(picture);
    }
}
