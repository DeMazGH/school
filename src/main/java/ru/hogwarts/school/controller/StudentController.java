package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentDataValidator;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentDataValidator studentDataValidator;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> findStudent(@PathVariable(name = "studentId") long studentId) {
        return ResponseEntity.ok(studentService.findStudent(studentId));
    }

    @GetMapping("/getStudentFaculty/{studentId}")
    public ResponseEntity<Faculty> getFacultyByStudentId(@PathVariable(name = "studentId") long studentId) {
        Faculty faculty = studentService.getFacultyByStudentId(studentId);
        if (faculty == null) {
            return ResponseEntity.ok().eTag("Student not assigned faculty").build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> findStudentsFromAge(@RequestParam int studentAge) {
        return ResponseEntity.ok(studentService.findByAge(studentAge));
    }

    @GetMapping("/findStudentByAgeInInterval")
    public ResponseEntity<Collection<Student>> findStudentsFromAgeInTheInterval(@RequestParam int minAge,
                                                                                @RequestParam int maxAge) {
        return ResponseEntity.ok(studentService.findStudentByAgeBetween(minAge, maxAge));
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        if (studentDataValidator.studentDataIsValid(student)) {
            return ResponseEntity.ok(studentService.updateStudent(student));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable(name = "studentId") long studentId) {
        studentService.deleteStudent(studentId);
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
