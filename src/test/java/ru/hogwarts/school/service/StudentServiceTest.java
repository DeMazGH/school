package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.hogwarts.school.Constant.*;

class StudentServiceTest {

    private final StudentService out;

    public StudentServiceTest(StudentService out) {
        this.out = out;
    }

    @Test
    void shouldReturnStudentInMethodCreate() {
        Student actual = out.createStudent(STUDENT_AAA);
        assertEquals(STUDENT_AAA, actual);
    }

    @Test
    void shouldReturnCorrectIdInMethodCreate() {
        Long actual = out.createStudent(STUDENT_AAA).getId();
        assertEquals(LONG_NUM_1, actual);

        actual = out.createStudent(STUDENT_CCC).getId();
        assertEquals(LONG_NUM_2, actual);
    }

    @Test
    void shouldReturnStudentInMethodFind() {
        out.createStudent(STUDENT_AAA);
        out.createStudent(STUDENT_BBB);

        Student actual = out.findStudent(LONG_NUM_1);
        assertEquals(STUDENT_AAA, actual);

        actual = out.findStudent(LONG_NUM_2);
        assertEquals(STUDENT_BBB, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodFindAllStudents() {
        Collection<Student> expected = new ArrayList<>();
        expected.add(STUDENT_AAA);
        expected.add(STUDENT_BBB);
        expected.add(STUDENT_CCC);

        out.createStudent(STUDENT_AAA);
        out.createStudent(STUDENT_BBB);
        out.createStudent(STUDENT_CCC);

        Collection<Student> actual = out.findAllStudents();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectListInMethodFindByAge() {
        Collection<Student> expected = new ArrayList<>();
        expected.add(STUDENT_AAA);
        expected.add(STUDENT_CCC);

        out.createStudent(STUDENT_AAA);
        out.createStudent(STUDENT_BBB);
        out.createStudent(STUDENT_CCC);

        Collection<Student> actual = out.findByAge(NUM_1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnStudentInMethodUpdateStudent() {
        out.createStudent(STUDENT_AAA);
        Student actual = out.updateStudent(STUDENT_AAA);
        assertEquals(STUDENT_AAA, actual);
    }
}