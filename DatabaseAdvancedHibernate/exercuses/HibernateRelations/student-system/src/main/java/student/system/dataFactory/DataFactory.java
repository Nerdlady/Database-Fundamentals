package student.system.dataFactory;

import student.system.domain.Course;
import student.system.domain.Homework;
import student.system.domain.Resource;
import student.system.domain.Student;
import student.system.domain.enums.ContentType;
import student.system.domain.enums.ResourceType;
import student.system.services.CourseService;
import student.system.services.HomeworkService;
import student.system.services.ResourceService;
import student.system.services.StudentService;

import java.math.BigDecimal;
import java.util.*;

public class DataFactory {
    private final CourseService courseService;
    private final HomeworkService homeworkService;
    private final ResourceService resourceService;
    private final StudentService studentService;

    public DataFactory(CourseService courseService, HomeworkService homeworkService, ResourceService resourceService, StudentService studentService) {
        this.courseService = courseService;
        this.homeworkService = homeworkService;
        this.resourceService = resourceService;
        this.studentService = studentService;
    }

    public void seedData() {
        if (!this.isDataSeeded()) {
            this.seedCources();
            this.seedStudents();
            this.seedHomeworks();
            this.seedResources();
        }
    }

    private void seedCources() {
        for (int i = 0; i < 5; i++) {
            Course course = new Course();
            course.setName("English " + i);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016, i, 10 + i);
            course.setStartDate(calendar.getTime());
            calendar.set(2017, i, 10 + i);
            course.setEndDate(calendar.getTime());
            course.setPrice(new BigDecimal("1" + i + "0.50"));

            this.courseService.persist(course);
        }
    }

    private void seedResources() {
        String[] resNames = {"Text", "Presentation", "File", "Inquiry", "Movie"};
        ResourceType[] resourceTypes = {ResourceType.OTHER, ResourceType.PRESENTATION, ResourceType.OTHER, ResourceType.OTHER, ResourceType.VIDEO};
        List<Course> courses = this.courseService.getCourses();
        for (int i = 0; i < 5; i++) {
            Resource resource = new Resource();
            resource.setName(resNames[i]);
            resource.setType(resourceTypes[i]);
            resource.setCourse(courses.get(i));
            resource.setURL("url " + i);
            this.resourceService.persist(resource);
        }
    }

    private void seedHomeworks() {
        String[] contents = {"Write essay", "Write words", "Write numbers", "Write sentences", "Write song"};
        List<Student> students = this.studentService.getStudents();
        List<Course> courses = this.courseService.getCourses();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Homework homework = new Homework();
            homework.setContent(contents[i]);
            homework.setContentType(ContentType.values()[random.nextInt(ContentType.values().length)]);
            homework.setSubmissionDate(new Date());
            homework.setStudent(students.get(i));
            homework.setCourse(courses.get(i));
            this.homeworkService.persist(homework);
        }
    }

    private void seedStudents() {
        String[] names = {"Pesho", "Gosho", "Ivan", "Stoqn", "Dragan"};
        List<Course> courses = this.courseService.getCourses();
        Set<Course> studentCourses = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setName(names[i]);
            Calendar calendar = Calendar.getInstance();
            calendar.set(1995, i, 5 + i);
            student.setBirthday(calendar.getTime());
            student.setPhoneNumber("12345678" + i);
            student.setRegistrationDate(new Date());
            studentCourses.clear();
            for (int j = 0; j < 2; j++) {
                studentCourses.add(courses.get(random.nextInt(courses.size())));
            }
            student.setStudentCourses(studentCourses);
            this.studentService.persist(student);
        }
    }

    private boolean isDataSeeded() {
        if (this.courseService.getCourses().size() <= 0) {
            return false;
        }

        if (this.homeworkService.getHomeworks().size() <= 0) {
            return false;
        }

        if (this.resourceService.getResources().size() <= 0) {
            return false;
        }

        if (this.studentService.getStudents().size() <= 0) {
            return false;
        }

        return true;
    }
}
