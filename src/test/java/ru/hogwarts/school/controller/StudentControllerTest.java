package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(studentController).isNotNull();
    }

    @Test
    void createStudentTest() {

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("TestFaculty1");
        faculty.setColor("TestColor");

        Student student = new Student();
        student.setAge(10);
        student.setName("TestStudent1");
        student.setFaculty(faculty);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port
                        + "/student", student, String.class))
                .isNotNull();

    }

    @Test
    void findStudentTest() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class))
                .isNotNull();
    }

    @Test
    void getFacultyByStudentIdTest() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?studentAge=1", String.class))
                .isNotNull();
    }

    @Test
    void findStudentsFromAgeTest() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/student/getStudentFaculty/1", String.class))
                .isNotNull();
    }

    @Test
    void findStudentsFromAgeInTheIntervalTest() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/student/findStudentByAgeInInterval?minAge=1&maxAge=20", String.class))
                .isNotNull();
    }

    @Test
    void updateStudentTest() {
    }

    @Test
    void removeStudentTest() {
    }
}