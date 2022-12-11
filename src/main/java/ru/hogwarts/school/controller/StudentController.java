package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> findStudent(@PathVariable long studentId) {
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> findStudentsFromAge(@RequestParam(required = false) Integer studentAge,
                                                                   @RequestParam(required = false) Integer minAge,
                                                                   @RequestParam(required = false) Integer maxAge) {
        if (studentAge != null) {
            return ResponseEntity.ok(studentService.findByAge(studentAge));
        }
        if (minAge != null && maxAge != null && minAge >= 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> removeStudent(@PathVariable long studentId) {
        studentService.removeStudent(studentId);
        return ResponseEntity.ok().build();
    }
}
