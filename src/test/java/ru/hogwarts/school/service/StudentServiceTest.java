package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static ru.hogwarts.school.Constant.*;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService out;

    @Mock
    private StudentRepository studentRepositoryMock;


    @Test
    void shouldReturnStudentInMethodCreate() {
        when(studentRepositoryMock.save(STUDENT_AAA)).thenReturn(STUDENT_AAA);
        Student actual = out.createStudent(STUDENT_AAA);
        assertEquals(STUDENT_AAA, actual);
    }

    @Test
    void shouldReturnStudentInMethodFind() {
        when(studentRepositoryMock.findById(LONG_NUM_2)).thenReturn(Optional.of(STUDENT_BBB));
        Student actual = out.findStudent(LONG_NUM_2);
        assertEquals(STUDENT_BBB, actual);
    }


    @Test
    void shouldReturnCorrectListInMethodFindAllStudents() {
        List<Student> expected = new ArrayList<>();
        expected.add(STUDENT_AAA);
        expected.add(STUDENT_BBB);
        expected.add(STUDENT_CCC);

        when(studentRepositoryMock.findAll()).thenReturn(expected);
        Collection<Student> actual = out.findAllStudents();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodFindByAge() {
        List<Student> expected = new ArrayList<>();
        expected.add(STUDENT_AAA);
        expected.add(STUDENT_CCC);

        List<Student> actual = new ArrayList<>();
        actual.add(STUDENT_AAA);
        actual.add(STUDENT_CCC);

        when(studentRepositoryMock.findByAge(NUM_1)).thenReturn(actual);

        actual = (List<Student>) out.findByAge(NUM_1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodFindByAgeBetween() {
        Collection<Student> expected = new ArrayList<>();
        expected.add(STUDENT_AAA);
        expected.add(STUDENT_CCC);

        Collection<Student> actual = new ArrayList<>();
        actual.add(STUDENT_AAA);
        actual.add(STUDENT_CCC);

        when(studentRepositoryMock.findByAgeBetween(NUM_1, NUM_1)).thenReturn(actual);

        actual = out.findByAgeBetween(NUM_1, NUM_1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnStudentInMethodUpdateStudent() {
        when(studentRepositoryMock.findById(LONG_NUM_1)).thenReturn(Optional.of(STUDENT_AAA));
        when(studentRepositoryMock.save(STUDENT_AAA)).thenReturn(STUDENT_AAA);
        Student actual = out.updateStudent(STUDENT_AAA);
        assertEquals(STUDENT_AAA, actual);
    }

    @Test
    void shouldReturnNullInMethodUpdateStudent() {
        when(studentRepositoryMock.findById(LONG_NUM_1)).thenReturn(Optional.empty());
        Student actual = out.updateStudent(STUDENT_AAA);
        assertNull(actual);
    }

    @Test
    void shouldReturnFacultyInMethodGetFacultyByStudentId() {
        when(studentRepositoryMock.findStudentById(LONG_NUM_1)).thenReturn(STUDENT_AAA);
        Faculty actual = out.getFacultyByStudentId(LONG_NUM_1);
        assertEquals(FACULTY_AAA, actual);
    }

    @Test
    void shouldReturnNullInMethodGetFacultyByStudentId() {
        when(studentRepositoryMock.findStudentById(LONG_NUM_1)).thenReturn(null);
        Faculty actual = out.getFacultyByStudentId(LONG_NUM_1);
        assertNull(actual);
    }

    @Test
    void shouldReturnLongNumInMethodGetAmountOfAllStudents() {
        when(studentRepositoryMock.getAmountOfAllStudents()).thenReturn(LONG_NUM_2);
        Long actual = out.getAmountOfAllStudents();
        assertEquals(LONG_NUM_2, actual);
    }

    @Test
    void shouldReturnDoubleNumInMethodGetAverageAgeOfAllStudents() {
        when(studentRepositoryMock.getAverageAgeOfAllStudents()).thenReturn(DOUBLE_NUM_1);
        Double actual = out.getAverageAgeOfAllStudents();
        assertEquals(DOUBLE_NUM_1, actual);
    }

    @Test
    void shouldReturnListOfStudentsInMethodGetFiveLatestStudents() {
        List<Student> students = new ArrayList<>();
        students.add(STUDENT_EEE);
        students.add(STUDENT_DDD);
        students.add(STUDENT_CCC);
        students.add(STUDENT_BBB);
        students.add(STUDENT_AAA);

        when(studentRepositoryMock.getFiveLatestStudents()).thenReturn(students);
        List<Student> actual = out.getFiveLatestStudents();
        assertEquals(students, actual);
    }

    @Test
    void shouldReturnListOfStudentsInMethodGetStudentsWithNameIsStartsFromA() {
        Student testStudent1 = new Student("Андрей", 18);
        Student testStudent2 = new Student("Анна", 19);
        Student testStudent3 = new Student("алексей", 20);

        List<Student> students = new ArrayList<>();
        students.add(STUDENT_AAA);
        students.add(STUDENT_BBB);
        students.add(STUDENT_CCC);
        students.add(testStudent1);
        students.add(testStudent2);
        students.add(testStudent3);

        List<String> expected = new ArrayList<>();
        expected.add(testStudent1.getName());
        expected.add(testStudent2.getName());

        when(studentRepositoryMock.findAll()).thenReturn(students);
        List<String> actual = out.getStudentsNameIsStartsFromA();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnDoubleNumInMethodGetAverageAgeOfAllStudentsWithStreams() {
        List<Student> students = new ArrayList<>();
        students.add(STUDENT_AAA);
        students.add(STUDENT_BBB);
        students.add(STUDENT_CCC);

        when(studentRepositoryMock.findAll()).thenReturn(students);

        Double expected = 1d * (STUDENT_AAA.getAge() + STUDENT_BBB.getAge() + STUDENT_CCC.getAge()) / students.size();
        Double actual = out.getAverageAgeOfAllStudentsWithStreams();
        assertEquals(expected, actual);
    }
}