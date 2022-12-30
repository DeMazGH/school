package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method - createStudent");
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("Was invoked method - findStudent");
        return studentRepository.findById(id).orElse(null);
    }

    public Collection<Student> findAllStudents() {
        logger.info("Was invoked method - findAllStudents");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(Integer age) {
        logger.info("Was invoked method - findByAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(Integer min, Integer max) {
        logger.info("Was invoked method - findByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method - updateStudent");
        if (studentRepository.findById(student.getId()).isPresent()) {
            return studentRepository.save(student);
        }
        return null;
    }

    public void removeStudent(Long id) {
        logger.info("Was invoked method - removeStudent");
        studentRepository.deleteById(id);
    }

    public Faculty getFacultyByStudentId(long studentId) {
        logger.info("Was invoked method - getFacultyByStudentId");
        Student desiredStudent = studentRepository.findStudentById(studentId);
        if (desiredStudent != null) {
            return desiredStudent.getFaculty();
        }
        return null;
    }

    public Long getAmountOfAllStudents() {
        logger.info("Was invoked method - getAmountOfAllStudents");
        return studentRepository.getAmountOfAllStudents();
    }

    public Double getAverageAgeOfAllStudents() {
        logger.info("Was invoked method - getAverageAgeOfAllStudents");
        return studentRepository.getAverageAgeOfAllStudents();
    }

    public List<Student> getFiveLatestStudents() {
        logger.info("Was invoked method - getFiveLatestStudents");
        return studentRepository.getFiveLatestStudents();
    }

    public List<String> getStudentsNameIsStartsFromA() {
        logger.info("Was invoked method - getStudentsNameIsStartsFromA");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(names -> names.startsWith("А"))
                .sorted()
                .toList();
    }
}
