package student.system.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.system.domain.Homework;
import student.system.repositories.HomeworkRepository;
import student.system.services.HomeworkService;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public void persist(Homework homework) {
        this.homeworkRepository.saveAndFlush(homework);
    }

    @Override
    public void delete(Homework homework) {
        this.homeworkRepository.delete(homework);
    }

    @Override
    public List<Homework> getHomeworks() {
        return this.homeworkRepository.findAll();
    }
}
