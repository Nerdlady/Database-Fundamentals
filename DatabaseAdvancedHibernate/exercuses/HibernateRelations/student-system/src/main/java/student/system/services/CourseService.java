package student.system.services;

import student.system.domain.Course;

import java.util.Date;
import java.util.List;

public interface CourseService {
    void persist(Course course);

    void delete(Course course);

    List<Course> getCourses();

    List<Course> getAllActive(Date date);

    List<Course> getAllWithResourcesMoreThan(int count);
}
