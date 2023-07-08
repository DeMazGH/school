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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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


    @Test
    void shouldReturnFacultyInMethodFindFaculty() {
        when(facultyRepositoryMock.findById(LONG_NUM_1)).thenReturn(Optional.of(FACULTY_AAA));
        Faculty actual = out.findFaculty(LONG_NUM_1);
        assertEquals(FACULTY_AAA, actual);
    }

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

        when(facultyRepositoryMock.findFacultyByColor(COLOR_AAA)).thenReturn(expected);
        Collection<Faculty> actual = out.findByColor(COLOR_AAA);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodFindFacultyByColorOrName() {
        List<Faculty> expected = new ArrayList<>();
        expected.add(FACULTY_AAA);

        when(facultyRepositoryMock.findFacultyByNameIgnoreCaseOrColorIgnoreCase(STRING_AAA, STRING_AAA)).thenReturn(expected);
        Collection<Faculty> actual = out.findFacultyByColorOrName(STRING_AAA);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnStudentInMethodUpdateFaculty() {
        when(facultyRepositoryMock.findById(LONG_NUM_1)).thenReturn(Optional.of(FACULTY_AAA));
        when(facultyRepositoryMock.save(FACULTY_AAA)).thenReturn(FACULTY_AAA);
        Faculty actual = out.updateFaculty(FACULTY_AAA);
        assertEquals(FACULTY_AAA, actual);
    }

    @Test
    void shouldReturnNullInMethodUpdateFaculty() {
        when(facultyRepositoryMock.findById(LONG_NUM_1)).thenReturn(Optional.empty());
        Faculty actual = out.updateFaculty(FACULTY_AAA);
        assertNull(actual);
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

    @Test
    void shouldReturnStringInMethodGetLongestFacultyName() {
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(FACULTY_AAA);
        faculties.add(FACULTY_BBB);
        faculties.add(FACULTY_LONGEST_NAME);

        when(facultyRepositoryMock.findAll()).thenReturn(faculties);

        String actual = out.getLongestFacultyName();
        assertEquals(NAME_LONGEST, actual);
    }
}