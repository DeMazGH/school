package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private Long lastId = 0L;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(Long id) {
        return students.get(id);
    }

    public Collection<Student> findAllStudents() {
        List<Student> studentList = new ArrayList<>(students.values());
        return Collections.unmodifiableList(studentList);
    }

    public Collection<Student> findByAge(int age) {
        return findAllStudents().stream().filter(student -> student.getAge() == age).toList();
    }

    public Student updateStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student removeStudent(Long id) {
        return students.remove(id);
    }
}
