package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Collection<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student updateStudent(Student student) {
        if (studentRepository.findById(student.getId()).isPresent()) {
            return studentRepository.save(student);
        }
        return null;
    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Faculty getFacultyByStudentId(long studentId) {
        Student desiredStudent = studentRepository.findStudentById(studentId);
        if (desiredStudent != null) {
            return desiredStudent.getFaculty();
        }
        return null;
    }
}
