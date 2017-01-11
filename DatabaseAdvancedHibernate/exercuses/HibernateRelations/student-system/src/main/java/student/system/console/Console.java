package student.system.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import student.system.dataFactory.DataFactory;
import student.system.domain.Course;
import student.system.domain.Homework;
import student.system.domain.Resource;
import student.system.domain.Student;
import student.system.services.CourseService;
import student.system.services.HomeworkService;
import student.system.services.ResourceService;
import student.system.services.StudentService;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Console implements CommandLineRunner {

    private final CourseService courseService;
    private final HomeworkService homeworkService;
    private final ResourceService resourceService;
    private final StudentService studentService;

    @Autowired
    public Console(CourseService courseService, HomeworkService homeworkService, ResourceService resourceService, StudentService studentService) {
        this.courseService = courseService;
        this.homeworkService = homeworkService;
        this.resourceService = resourceService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... strings) throws Exception {
        DataFactory dataFactory = new DataFactory(this.courseService,this.homeworkService,this.resourceService,this.studentService);
        dataFactory.seedData();

        List<Student> students = this.studentService.getStudents();

        for (Student student : students) {
            System.out.println(student.getName());
            for (Homework homework : student.getHomeworks()) {
                System.out.printf("Content: %s Type: %s%n",homework.getContent(),homework.getContentType().toString());
            }
        }

        List<Course> courses = this.courseService.getCourses().stream().sorted((a,b) -> {
            int result = a.getStartDate().compareTo(b.getStartDate());

            if (result == 0){
                result = b.getEndDate().compareTo(a.getEndDate());
            }
            return result;
        }).collect(Collectors.toList());

        for (Course course : courses) {
            System.out.printf("Name: %s Description: %d%n",course.getName(),course.getDescription());
            for (Resource resource : course.getResources()) {
                System.out.printf("--Name: %s Type: %s URL: %s%n",resource.getName(),resource.getType().toString(),resource.getURL());
            }
        }

        List<Course> coursesWithCountMoreThan = this.courseService.getAllWithResourcesMoreThan(5);

        for (Course course : coursesWithCountMoreThan) {
            System.out.printf("%s - %d%n",course.getName(),course.getResources().size());
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,10,15);
        List<Course> activeCourses = this.courseService.getAllActive(calendar.getTime()).stream().sorted((a,b) -> {
          int result = Integer.compare(b.getStudents().size(),a.getStudents().size());
            if (result == 0){
                long diff = (a.getEndDate().getTime() - a.getStartDate().getTime());
                long diff2 = (b.getEndDate().getTime() - b.getStartDate().getTime());

                result = Long.compare(diff2,diff);
            }
            return result;

        }).collect(Collectors.toList());

        for (Course activeCourse : activeCourses) {
            long diff = (activeCourse.getEndDate().getTime() - activeCourse.getStartDate().getTime());
            System.out.printf("Name: %s ,Start: %s, End: %s, Diff days: %s  Number of students: %d%n",
                    activeCourse.getName(),
                    activeCourse.getStartDate().toString(),
                    activeCourse.getEndDate().toString(),
                    diff / (1000*60*60*24),
                    activeCourse.getStudents().size()
            );
        }

        List<Student> orderedStudents = students.stream().sorted((a,b) ->{
            BigDecimal totalPriceOne = this.sumPrice(a.getStudentCourses());
            BigDecimal totalPriceTwo = this.sumPrice(b.getStudentCourses());

            int result = totalPriceTwo.compareTo(totalPriceOne);

            if (result == 0){
                result = Integer.compare(b.getStudentCourses().size(),a.getStudentCourses().size());
            }

            if (result == 0){
                result = a.getName().compareTo(b.getName());
            }

            return result;
        }).collect(Collectors.toList());

        for (Student orderedStudent : orderedStudents) {
            BigDecimal totalPrice = this.sumPrice(orderedStudent.getStudentCourses());
            BigDecimal avg = new BigDecimal("0");
            if (totalPrice.compareTo(new BigDecimal("0")) == 1){
              avg = totalPrice.divide(new BigDecimal(Integer.toString(orderedStudent.getStudentCourses().size())),BigDecimal.ROUND_DOWN);
            }

            System.out.printf("Name: %s Number of courses: %d Total price: %s Avg price: %s%n",
                    orderedStudent.getName(),
                    orderedStudent.getStudentCourses().size(),
                    totalPrice,
                   avg.toString()
            );
        }

    }

    private BigDecimal sumPrice(Set<Course> courses){
        BigDecimal sum = new BigDecimal("0");
        for (Course course : courses) {
           sum = sum.add(course.getPrice());
        }
        return sum;
    }
}
