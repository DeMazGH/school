package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(Integer age);

    Collection<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    Student findStudentById(long id);

    Collection<Student> findStudentByFacultyId(long facultyId);
}
