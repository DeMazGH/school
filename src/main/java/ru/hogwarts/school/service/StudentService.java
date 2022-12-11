package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Collection<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(Integer age) {
        return findAllStudents().stream().filter(student -> Objects.equals(student.getAge(), age)).toList();
    }

    public Collection<Student> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Faculty getFacultyByStudentId(long studentId) {
        return studentRepository.findStudentById(studentId).getFaculty();
    }
}
