package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.hogwarts.school.Constant.*;

class FacultyServiceTest {

    private final FacultyService out = new FacultyService();

    @Test
    void shouldReturnFacultyInMethodCreateFaculty() {
        Faculty actual = out.createFaculty(FACULTY_AAA);
        Assertions.assertEquals(FACULTY_AAA, actual);
    }

    @Test
    void shouldReturnCorrectIdInMethodCreateFaculty() {
        Long actual = out.createFaculty(FACULTY_AAA).getId();
        assertEquals(LONG_NUM_1, actual);

        actual = out.createFaculty(FACULTY_CCC).getId();
        assertEquals(LONG_NUM_2, actual);
    }

    @Test
    void shouldReturnFacultyInMethodFindFaculty() {
        out.createFaculty(FACULTY_AAA);
        out.createFaculty(FACULTY_CCC);

        Faculty actual = out.findFaculty(LONG_NUM_1);
        Assertions.assertEquals(FACULTY_AAA, actual);

        actual = out.findFaculty(LONG_NUM_2);
        Assertions.assertEquals(FACULTY_CCC, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodFindAllFaculties() {
        Collection<Faculty> expected = new ArrayList<>();
        expected.add(FACULTY_AAA);
        expected.add(FACULTY_BBB);
        expected.add(FACULTY_CCC);

        out.createFaculty(FACULTY_AAA);
        out.createFaculty(FACULTY_BBB);
        out.createFaculty(FACULTY_CCC);

        Collection<Faculty> actual = out.findAllFaculties();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodFindByColor() {
        Collection<Faculty> expected = new ArrayList<>();
        expected.add(FACULTY_AAA);
        expected.add(FACULTY_CCC);

        out.createFaculty(FACULTY_AAA);
        out.createFaculty(FACULTY_BBB);
        out.createFaculty(FACULTY_CCC);

        Collection<Faculty> actual = out.findByColor(COLOR_AAA);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnStudentInMethodUpdateFaculty() {
        out.createFaculty(FACULTY_AAA);
        Faculty actual = out.updateFaculty(FACULTY_AAA);
        Assertions.assertEquals(FACULTY_AAA, actual);
    }

    @Test
    void shouldReturnStudentInMethodRemoveFaculty() {
        out.createFaculty(FACULTY_AAA);
        Faculty actual = out.removeFaculty(LONG_NUM_1);
        Assertions.assertEquals(FACULTY_AAA, actual);
    }
}