package student.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import student.system.domain.Course;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("select c from student.system.domain.Course as c where c.resources.size > :count")
    List<Course> findAllByResourcesCountMoreThan(@Param("count") int count);

    @Query("select c from student.system.domain.Course as c where c.startDate < :date and c.endDate > :date")
    List<Course> findAllWhichAreActive(@Param("date") Date date);
}