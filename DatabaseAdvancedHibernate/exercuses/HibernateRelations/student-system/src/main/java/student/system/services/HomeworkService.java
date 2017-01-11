package student.system.services;

import student.system.domain.Course;
import student.system.domain.Homework;

import java.util.List;

public interface HomeworkService {
    void persist(Homework homework);

    void delete(Homework homework);

    List<Homework> getHomeworks();
}
