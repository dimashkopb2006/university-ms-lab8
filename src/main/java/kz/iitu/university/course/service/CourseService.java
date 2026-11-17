package kz.iitu.university.course.service;

import kz.iitu.university.course.entity.Course;
import kz.iitu.university.course.repository.CourseRepository;
import kz.iitu.university.exception.ConflictException;
import kz.iitu.university.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course " + id + " not found"));
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public Course create(Course course) {
        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new ConflictException("Course code already exists: " + course.getCourseCode());
        }
        course.setId(null);
        return courseRepository.save(course);
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public Course update(Long id, Course data) {
        Course course = getById(id);
        if (!course.getCourseCode().equals(data.getCourseCode())
                && courseRepository.existsByCourseCode(data.getCourseCode())) {
            throw new ConflictException("Course code already exists: " + data.getCourseCode());
        }
        course.setCourseName(data.getCourseName());
        course.setCourseCode(data.getCourseCode());
        course.setCredits(data.getCredits());
        course.setInstructor(data.getInstructor());
        return courseRepository.save(course);
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public void delete(Long id) {
        courseRepository.delete(getById(id));
    }
}
