package kz.iitu.university.course.repository;

import kz.iitu.university.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseCode(String courseCode);
}
