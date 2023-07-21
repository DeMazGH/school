package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Collection<Student> findStudentByAgeBetween(int minAge, int maxAge);

    Collection<Student> findStudentByFacultyId(long facultyId);

    @Query(value = "select count(*) from student", nativeQuery = true)
    Long getAmountOfAllStudents();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    Double getAverageAgeOfAllStudents();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    List<Student> getFiveLatestStudents();

    @Query(value = "select name from student where name like 'A%' or name like 'А%'", nativeQuery = true)
    List<String> getStudentsWhoseNamesIsStartsWithA();
}
