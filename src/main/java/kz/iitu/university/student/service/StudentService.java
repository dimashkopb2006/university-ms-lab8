package kz.iitu.university.student.service;

import kz.iitu.university.exception.ConflictException;
import kz.iitu.university.exception.NotFoundException;
import kz.iitu.university.student.entity.Student;
import kz.iitu.university.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {   // constructor injection
        this.studentRepository = studentRepository;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student " + id + " not found"));
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public Student create(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email already exists: " + student.getEmail());
        }
        student.setId(null);
        return studentRepository.save(student);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public Student update(Long id, Student data) {
        Student student = getById(id);
        if (!student.getEmail().equals(data.getEmail()) && studentRepository.existsByEmail(data.getEmail())) {
            throw new ConflictException("Email already exists: " + data.getEmail());
        }
        student.setFirstName(data.getFirstName());
        student.setLastName(data.getLastName());
        student.setEmail(data.getEmail());
        student.setMajor(data.getMajor());
        student.setEnrollmentYear(data.getEnrollmentYear());
        return studentRepository.save(student);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public void delete(Long id) {
        studentRepository.delete(getById(id));
    }
}
