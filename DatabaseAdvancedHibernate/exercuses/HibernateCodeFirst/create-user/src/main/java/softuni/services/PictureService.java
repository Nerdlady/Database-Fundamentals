package softuni.services;

import softuni.entities.Picture;

public interface PictureService {
    void persist(Picture picture);

    void save(Picture picture);
}
