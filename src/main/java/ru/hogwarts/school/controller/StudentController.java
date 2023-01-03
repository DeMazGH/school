package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/getStudentFaculty/{studentId}")
    public ResponseEntity<Faculty> getFacultyByStudentId(@PathVariable long studentId) {
        Faculty faculty = studentService.getFacultyByStudentId(studentId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> findStudentsFromAge(@RequestParam Integer studentAge) {
        return ResponseEntity.ok(studentService.findByAge(studentAge));
    }

    @GetMapping("/findStudentByAgeInInterval")
    public ResponseEntity<Collection<Student>> findStudentsFromAgeInTheInterval(@RequestParam Integer minAge,
                                                                                @RequestParam Integer maxAge) {
        return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> removeStudent(@PathVariable long studentId) {
        studentService.removeStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getFiveLatestStudents")
    public ResponseEntity<Collection<Student>> getFiveLatestStudents() {
        return ResponseEntity.ok(studentService.getFiveLatestStudents());
    }

    @GetMapping("/getAverageAgeOfAllStudents")
    public ResponseEntity<Double> getAverageAgeOfAllStudents() {
        return ResponseEntity.ok(studentService.getAverageAgeOfAllStudents());
    }

    @GetMapping("/getAmountOfAllStudents")
    public ResponseEntity<Long> getAmountOfAllStudents() {
        return ResponseEntity.ok(studentService.getAmountOfAllStudents());
    }

    @GetMapping("/getStudentsNameIsStartsFromA")
    public ResponseEntity<Collection<String>> getStudentsNameIsStartsFromA() {
        return ResponseEntity.ok(studentService.getStudentsNameIsStartsFromA());
    }

    @GetMapping("/getAverageAgeOfAllStudentsWithStreams")
    public ResponseEntity<Double> getAverageAgeOfAllStudentsWithStreams() {
        return ResponseEntity.ok(studentService.getAverageAgeOfAllStudentsWithStreams());
    }

    @GetMapping("/printInConsoleListOfStudentsNamesWithThreads")
    public ResponseEntity<String> printInConsoleListOfStudentsNamesWithThreads() {
        studentService.printInConsoleListOfStudentsNamesWithThreads();
        return ResponseEntity.ok("Результат в консоли");
    }

    @GetMapping("/printInConsoleListOfStudentsNamesWithSynchronizedThreads")
    public ResponseEntity<String> printInConsoleListOfStudentsNamesWithSynchronizedThreads() {
        studentService.printInConsoleListOfStudentsNamesWithSynchronizedThreads();
        return ResponseEntity.ok("Результат в консоли");
    }
}
