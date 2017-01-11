package student.system.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.system.domain.Course;
import student.system.repositories.CourseRepository;
import student.system.services.CourseService;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void persist(Course course) {
        this.courseRepository.saveAndFlush(course);
    }

    @Override
    public void delete(Course course) {
        this.courseRepository.delete(course);
    }

    @Override
    public List<Course> getCourses() {
        return this.courseRepository.findAll();
    }

    @Override
    public List<Course> getAllActive(Date date) {
        return this.courseRepository.findAllWhichAreActive(date);
    }

    @Override
    public List<Course> getAllWithResourcesMoreThan(int count) {
        return this.courseRepository.findAllByResourcesCountMoreThan(count);
    }
}
