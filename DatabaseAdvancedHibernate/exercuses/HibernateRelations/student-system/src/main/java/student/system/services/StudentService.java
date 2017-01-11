package student.system.services;

import student.system.domain.Student;

import java.util.List;

public interface StudentService {
    void persist(Student student);

    void delete(Student student);

    List<Student> getStudents();
}
