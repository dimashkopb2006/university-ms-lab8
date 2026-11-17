package kz.iitu.university.grade.controller;

import jakarta.validation.Valid;
import kz.iitu.university.grade.entity.Grade;
import kz.iitu.university.grade.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<Grade> assign(@Valid @RequestBody Grade grade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.assign(grade));
    }

    @GetMapping("/student/{id}")
    public List<Grade> byStudent(@PathVariable Long id) {
        return gradeService.getByStudent(id);
    }
}
