package kz.iitu.university.grade.service;

import kz.iitu.university.course.entity.Course;
import kz.iitu.university.course.service.CourseService;
import kz.iitu.university.dto.StudentTranscriptDto;
import kz.iitu.university.grade.entity.Grade;
import kz.iitu.university.grade.repository.GradeRepository;
import kz.iitu.university.student.entity.Student;
import kz.iitu.university.student.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public GradeService(GradeRepository gradeRepository,
                        StudentService studentService,
                        CourseService courseService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Transactional(transactionManager = "gradeTransactionManager")
    public Grade assign(Grade grade) {
        studentService.getById(grade.getStudentId());
        courseService.getById(grade.getCourseId());

        grade.setId(null);
        grade.setLetterGrade(toLetter(grade.getScore()));
        grade.setGradedAt(LocalDateTime.now());
        return gradeRepository.save(grade);
    }

    public List<Grade> getByStudent(Long studentId) {
        studentService.getById(studentId);
        return gradeRepository.findByStudentId(studentId);
    }

    public StudentTranscriptDto getTranscript(Long studentId) {
        Student student = studentService.getById(studentId);
        List<Grade> grades = gradeRepository.findByStudentId(studentId);

        List<StudentTranscriptDto.CourseResult> courses = new ArrayList<>();
        double sum = 0;
        for (Grade g : grades) {
            Course c = courseService.getById(g.getCourseId());
            courses.add(new StudentTranscriptDto.CourseResult(
                    c.getCourseCode(), c.getCourseName(), g.getScore(), g.getLetterGrade()));
            sum += g.getScore();
        }
        Double average = grades.isEmpty() ? null : sum / grades.size();

        return new StudentTranscriptDto(student.getId(),
                student.getFirstName() + " " + student.getLastName(), courses, average);
    }

    private String toLetter(int score) {
        if (score >= 90) return "A";
        if (score >= 75) return "B";
        if (score >= 60) return "C";
        if (score >= 50) return "D";
        return "F";
    }
}
