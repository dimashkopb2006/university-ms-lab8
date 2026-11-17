package kz.iitu.university.grade.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "grades")   // grade_db
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Logical references to other databases (NOT foreign keys)
    @NotNull
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @NotNull
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "score", nullable = false)
    private Integer score;

    // Filled by GradeService, not by the client
    @Column(name = "letter_grade")
    private String letterGrade;

    @Column(name = "graded_at")
    private LocalDateTime gradedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }
    public LocalDateTime getGradedAt() { return gradedAt; }
    public void setGradedAt(LocalDateTime gradedAt) { this.gradedAt = gradedAt; }
}
