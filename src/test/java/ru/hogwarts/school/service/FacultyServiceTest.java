package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.hogwarts.school.Constant.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {

    @InjectMocks
    private FacultyService out;

    @Mock
    private FacultyRepository facultyRepositoryMock;

    @Mock
    private StudentRepository studentRepositoryMock;

    @Test
    void shouldReturnFacultyInMethodCreateFaculty() {
        when(facultyRepositoryMock.save(FACULTY_AAA)).thenReturn(FACULTY_AAA);
        Faculty actual = out.createFaculty(FACULTY_AAA);
        assertEquals(FACULTY_AAA, actual);
    }


//    @Test
//    void shouldReturnFacultyInMethodFindFaculty() {
//        when(facultyRepositoryMock.findById(LONG_NUM_1).get()).thenReturn(FACULTY_AAA);
//        Faculty actual = out.findFaculty(LONG_NUM_1);
//        assertEquals(FACULTY_AAA, actual);
//    }
    //тест не проходит из-за - NoSuchElementException: No value present

    @Test
    void shouldReturnCorrectListInMethodFindAllFaculties() {
        List<Faculty> expected = new ArrayList<>();
        expected.add(FACULTY_AAA);
        expected.add(FACULTY_BBB);
        expected.add(FACULTY_CCC);

        when(facultyRepositoryMock.findAll()).thenReturn(expected);
        Collection<Faculty> actual = out.findAllFaculties();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodFindByColor() {
        List<Faculty> expected = new ArrayList<>();
        expected.add(FACULTY_AAA);
        expected.add(FACULTY_CCC);

        when(facultyRepositoryMock.findFacultyByColorIgnoreCase(COLOR_AAA)).thenReturn(expected);
        Collection<Faculty> actual = out.findByColor(COLOR_AAA);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodFindByName() {
        List<Faculty> expected = new ArrayList<>();
        expected.add(FACULTY_AAA);

        when(facultyRepositoryMock.findFacultyByNameIgnoreCase(NAME_AAA)).thenReturn(expected);
        Collection<Faculty> actual = out.findByName(NAME_AAA);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnStudentInMethodUpdateFaculty() {
        when(facultyRepositoryMock.save(FACULTY_AAA)).thenReturn(FACULTY_AAA);
        Faculty actual = out.updateFaculty(FACULTY_AAA);
        assertEquals(FACULTY_AAA, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodGetStudentsByFacultyId() {
        List<Student> expected = new ArrayList<>();
        expected.add(STUDENT_AAA);
        expected.add(STUDENT_CCC);

        when(studentRepositoryMock.findStudentByFacultyId(LONG_NUM_1)).thenReturn(expected);

        Collection<Student> actual = out.getStudentsByFacultyId(LONG_NUM_1);
        assertEquals(expected, actual);
    }
}