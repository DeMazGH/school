package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private volatile Integer count = 0;

    public Student createStudent(Student student) {
        log.debug("Was invoked method - createStudent");
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        log.debug("Was invoked method - findStudent");
        return studentRepository.findById(id).orElse(null);
    }

    public Collection<Student> findAllStudents() {
        log.debug("Was invoked method - findAllStudents");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(Integer age) {
        log.debug("Was invoked method - findByAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(Integer min, Integer max) {
        log.debug("Was invoked method - findByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student updateStudent(Student student) {
        log.debug("Was invoked method - updateStudent");
        if (studentRepository.findById(student.getId()).isPresent()) {
            return studentRepository.save(student);
        }
        return null;
    }

    public void removeStudent(Long id) {
        log.debug("Was invoked method - removeStudent");
        studentRepository.deleteById(id);
    }

    public Faculty getFacultyByStudentId(long studentId) {
        log.debug("Was invoked method - getFacultyByStudentId");
        Student desiredStudent = studentRepository.findStudentById(studentId);
        if (desiredStudent != null) {
            return desiredStudent.getFaculty();
        }
        return null;
    }

    public Long getAmountOfAllStudents() {
        log.debug("Was invoked method - getAmountOfAllStudents");
        return studentRepository.getAmountOfAllStudents();
    }

    public Double getAverageAgeOfAllStudents() {
        log.debug("Was invoked method - getAverageAgeOfAllStudents");
        return studentRepository.getAverageAgeOfAllStudents();
    }

    public List<Student> getFiveLatestStudents() {
        log.debug("Was invoked method - getFiveLatestStudents");
        return studentRepository.getFiveLatestStudents();
    }

    public List<String> getStudentsNameIsStartsFromA() {
        log.debug("Was invoked method - getStudentsNameIsStartsFromA");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(names -> names.startsWith("А"))
                .sorted()
                .toList();
    }

    public Double getAverageAgeOfAllStudentsWithStreams() {
        log.debug("Was invoked method - getAverageAgeOfAllStudentsWithStreams");
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(Double.NaN);
    }

    public void printInConsoleListOfStudentsNamesWithThreads() {
        List<Student> studentList = studentRepository.findAll();

        System.out.println(studentList.get(0).getName());
        System.out.println(studentList.get(1).getName());

        new Thread(() -> {
            System.out.println(studentList.get(2).getName());
            System.out.println(studentList.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println(studentList.get(4).getName());
            System.out.println(studentList.get(5).getName());
        }).start();
    }

    public void printInConsoleListOfStudentsNamesWithSynchronizedThreads() {
        List<Student> studentList = studentRepository.findAll();

        printStudentName(studentList);
        printStudentName(studentList);

        new Thread(() -> {
            printStudentName(studentList);
            printStudentName(studentList);
        }).start();

        new Thread(() -> {
            printStudentName(studentList);
            printStudentName(studentList);
        }).start();
    }

    private synchronized void printStudentName(List<Student> studentList) {
        System.out.println(studentList.get(count).getName());
        count++;
    }
}
