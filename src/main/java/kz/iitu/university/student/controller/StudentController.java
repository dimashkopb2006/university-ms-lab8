package kz.iitu.university.student.controller;

import jakarta.validation.Valid;
import kz.iitu.university.dto.StudentTranscriptDto;
import kz.iitu.university.grade.service.GradeService;
import kz.iitu.university.student.entity.Student;
import kz.iitu.university.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final GradeService gradeService;

    public StudentController(StudentService studentService, GradeService gradeService) {
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(student));
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/transcript")
    public StudentTranscriptDto transcript(@PathVariable Long id) {
        return gradeService.getTranscript(id);
    }
}
