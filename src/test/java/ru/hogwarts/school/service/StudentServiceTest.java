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
}