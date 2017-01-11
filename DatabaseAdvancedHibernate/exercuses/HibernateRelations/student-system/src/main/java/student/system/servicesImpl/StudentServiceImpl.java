package student.system.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.system.domain.Student;
import student.system.repositories.StudentRepository;
import student.system.services.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void persist(Student student) {
        this.studentRepository.saveAndFlush(student);
    }

    @Override
    public void delete(Student student) {
        this.studentRepository.delete(student);
    }

    @Override
    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }
}
