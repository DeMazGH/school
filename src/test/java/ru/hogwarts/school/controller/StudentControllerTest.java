package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.net.URI;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

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
    void testCreateStudent() {
        Student student = givenStudentWith("StudentName", 15);
        ResponseEntity<Student> response = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(response);
    }

    @Test
    void testFindStudent() {
        Student student = givenStudentWith("StudentName", 15);
        ResponseEntity<Student> createResponse = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(createResponse);

        Student createdStudent = createResponse.getBody();
        thenStudentWithIdHasBeenFound(createdStudent.getId(), createdStudent);
    }

    @Test
    void testGetFacultyByStudentId() {
        Faculty faculty = new Faculty("faculty", "red");
        Student student = new Student("StudentName", 15, faculty);
        ResponseEntity<Student> createResponse = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(createResponse);


        ResponseEntity<Faculty> receivedFaculty = whenFindingFacultyByStudentIdRequest(student);
        thenFacultyHasBeenFind(receivedFaculty);
    }

    @Test
    void testFindStudentsFromAge() {
        Student student15 = givenStudentWith("StudentName1", 15);
        Student student18 = givenStudentWith("StudentName2", 18);
        Student student22 = givenStudentWith("StudentName3", 22);
        Student student25 = givenStudentWith("StudentName4", 25);

        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student15);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student18);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student22);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student25);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("age", "18");
        thenStudentsAreFoundByCriteria(queryParams, student18);
    }

    @Test
    void testFindStudentsFromAgeInTheInterval() {
        Student student15 = givenStudentWith("StudentName1", 15);
        Student student18 = givenStudentWith("StudentName2", 18);
        Student student22 = givenStudentWith("StudentName3", 22);
        Student student25 = givenStudentWith("StudentName4", 25);

        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student15);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student18);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student22);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student25);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("minAge", "16");
        queryParams.add("maxAge", "24");
        thenStudentsAreFoundByCriteria(queryParams, student18, student22);
    }

    @Test
    void testUpdateStudent() {
        Student student = givenStudentWith("StudentName1", 15);

        ResponseEntity<Student> createResponse = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(createResponse);
        Student createdStudent = createResponse.getBody();

        whenUpdatingStudent(createdStudent, 23, "newName");
        thenStudentHasBeenUpdated(createdStudent, 23, "newName");
    }

    @Test
    void testRemoveStudent() {
        Student student = givenStudentWith("StudentName1", 15);

        ResponseEntity<Student> createResponse = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(createResponse);
        Student createdStudent = createResponse.getBody();

        whenDeletingStudent(createdStudent);
        thenStudentNotFound(createdStudent);
    }

    private Student givenStudentWith(String name, int age) {
        return new Student(name, age);
    }

    private ResponseEntity<Student> whenSendingCreateStudentRequest(URI uri, Student student) {
        return restTemplate.postForEntity(uri, student, Student.class);
    }

    private UriComponentsBuilder getUriBuilder() {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path("/hogwarts/student");
    }

    private ResponseEntity<Faculty> whenFindingFacultyByStudentIdRequest(Student student) {
        URI uri = getUriBuilder().path("/getStudentFaculty/{studentId}").buildAndExpand(student.getId()).toUri();
        return restTemplate.getForEntity(uri, Faculty.class);
    }

    private void thenFacultyHasBeenFind(ResponseEntity<Faculty> response) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
    }

    private void thenStudentHasBeenCreated(ResponseEntity<Student> response) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();
    }

    private void thenStudentWithIdHasBeenFound(Long studentId, Student student) {
        URI uri = getUriBuilder().path("/{id}").buildAndExpand(studentId).toUri();
        ResponseEntity<Student> response = restTemplate.getForEntity(uri, Student.class);

        Assertions.assertThat(response.getBody()).isEqualTo(student);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private void thenStudentsAreFoundByCriteria(MultiValueMap<String, String> queryParams, Student... student) {
        URI uri = getUriBuilder().queryParams(queryParams).build().toUri();

        ResponseEntity<Collection<Student>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<Student>>() {
                }
        );

        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Collection<Student> actualResult = response.getBody();
        resetIds(actualResult);
        Assertions.assertThat(actualResult).containsExactlyInAnyOrder(student);
    }

    private void resetIds(Collection<Student> students) {
        students.forEach(it -> it.setId(null));
    }

    private void whenUpdatingStudent(Student createdStudent, int newAge, String newName) {
        createdStudent.setAge(newAge);
        createdStudent.setName(newName);

        restTemplate.put(getUriBuilder().build().toUri(), createdStudent);
    }

    private void thenStudentHasBeenUpdated(Student createdStudent, int newAge, String newName) {
        URI getUri = getUriBuilder().path("/{id}").buildAndExpand(createdStudent.getId()).toUri();
        ResponseEntity<Student> updatedStudentResponse = restTemplate.getForEntity(getUri, Student.class);

        Assertions.assertThat(updatedStudentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(updatedStudentResponse.getBody()).isNotNull();
        Assertions.assertThat(updatedStudentResponse.getBody().getAge()).isEqualTo(newAge);
        Assertions.assertThat(updatedStudentResponse.getBody().getName()).isEqualTo(newName);
    }

    private void whenDeletingStudent(Student createdStudent) {
        restTemplate.delete(getUriBuilder().path("/{id}").buildAndExpand(createdStudent.getId()).toUri());
    }

    private void thenStudentNotFound(Student createdStudent) {
        URI getUri = getUriBuilder().path("/{id}").buildAndExpand(createdStudent.getId()).toUri();
        ResponseEntity<Student> emptyResponse = restTemplate.getForEntity(getUri, Student.class);

        Assertions.assertThat(emptyResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}