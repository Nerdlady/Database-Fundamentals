package softuni.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Tag;
import softuni.repositories.TagDao;
import softuni.services.TagService;

@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public void persist(Tag tag) {
        this.tagDao.saveAndFlush(tag);
    }
}
