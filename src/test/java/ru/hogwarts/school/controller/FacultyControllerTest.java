package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.hogwarts.school.Constant.*;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private FacultyService facultyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFacultyTest() throws Exception {
        String facultyName = "facultyName";
        String facultyColor = "facultyColor";
        Long id = 1L;

        JSONObject facultyObject = createFacultyJson(facultyName, facultyColor);
        Faculty faculty = createFaculty(id, facultyName, facultyColor);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(facultyName))
                .andExpect(jsonPath("$.color").value(facultyColor));
    }

    @Test
    void findFacultyTest() throws Exception {
        String facultyName = "facultyName";
        String facultyColor = "facultyColor";
        Long id = 1L;

        Faculty faculty = createFaculty(id, facultyName, facultyColor);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(facultyName))
                .andExpect(jsonPath("$.color").value(facultyColor));
    }

    @Test
    void findFacultiesByColorTest() throws Exception {
        String facultyName1 = "facultyNameOne";
        Long id1 = 1L;

        String facultyName2 = "facultyNameTwo";
        Long id2 = 2L;

        String facultyColor = "facultyColor";

        Faculty faculty1 = createFaculty(id1, facultyName1, facultyColor);
        Faculty faculty2 = createFaculty(id2, facultyName2, facultyColor);

        when(facultyRepository.findFacultyByColor(facultyColor)).thenReturn(List.of(faculty1, faculty2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findByColor")
                        .queryParam("color", facultyColor)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1, faculty2))));
    }

    @Test
    void findFacultiesByColorOrNameTest() throws Exception {
        Long id1 = 1L;
        String facultyName1 = "facultyNameOne";
        String facultyColor1 = "facultyColor1";
        String facultyColor1IgnoreCase = "FacULtyColOr1";

        Long id2 = 2L;
        String facultyName2 = "facultyNameTwo";
        String facultyName2IgnoreCase = "FACULTYNameTWO";
        String facultyColor2 = "facultyColor2";

        Faculty faculty1 = createFaculty(id1, facultyName1, facultyColor1);
        Faculty faculty2 = createFaculty(id2, facultyName2, facultyColor2);

        when(facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(facultyColor1IgnoreCase,
                facultyColor1IgnoreCase)).thenReturn(List.of(faculty1));
        when(facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(facultyName2IgnoreCase,
                facultyName2IgnoreCase)).thenReturn(List.of(faculty2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findByColorOrName")
                        .queryParam("nameOrColor", facultyColor1IgnoreCase)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1))));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findByColorOrName")
                        .queryParam("nameOrColor", facultyName2IgnoreCase)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty2))));
    }

    @Test
    void getStudentsByFacultyTest() throws Exception {
        Long id = 1L;
        String facultyName = "facultyNameOne";
        String facultyColor = "facultyColor1";

        Faculty faculty = createFaculty(id, facultyName, facultyColor);

        String studentName1 = "studentNameOne";
        Integer studentAge1 = 15;
        String studentName2 = "studentNameTwo";
        Integer studentAge2 = 12;

        Student student1 = new Student(studentName1, studentAge1, faculty);
        Student student2 = new Student(studentName2, studentAge2, faculty);

        when(studentRepository.findStudentByFacultyId(id)).thenReturn(List.of(student1, student2));
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{facultyId}/students", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(student1, student2))));
    }

    @Test
    void updateFacultyTest() throws Exception {
        Long id = 1L;
        String facultyName = "facultyName";
        String facultyColor = "facultyColor";

        String newFacultyName = "newFacultyName";
        String newFacultyColor = "newFacultyColor";

        JSONObject newFacultyObject = createFacultyJson(newFacultyName, newFacultyColor);
        newFacultyObject.put("id", id);

        Faculty faculty = createFaculty(id, facultyName, facultyColor);
        Faculty newFaculty = createFaculty(id, newFacultyName, newFacultyColor);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(any(Faculty.class))).thenReturn(newFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(newFacultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(newFacultyName))
                .andExpect(jsonPath("$.color").value(newFacultyColor));
    }

    @Test
    void removeFacultyTest() throws Exception {
        Long id = 1L;
        String facultyName = "facultyName";
        String facultyColor = "facultyColor";

        Faculty faculty = createFaculty(id, facultyName, facultyColor);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{facultyId}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(facultyRepository, atLeastOnce()).deleteById(id);
    }

    private Faculty createFaculty(Long id, String facultyName, String facultyColor) {
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);
        return faculty;
    }

    private JSONObject createFacultyJson(String facultyName, String facultyColor) throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", facultyName);
        facultyObject.put("color", facultyColor);
        return facultyObject;
    }

    @Test
    void getLongestFacultyNameTest() throws Exception {
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(FACULTY_AAA);
        faculties.add(FACULTY_BBB);
        faculties.add(FACULTY_LONGEST_NAME);

        when(facultyRepository.findAll()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/getLongestFacultyName")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(NAME_LONGEST));

        verify(facultyRepository, atLeastOnce()).findAll();
    }
}