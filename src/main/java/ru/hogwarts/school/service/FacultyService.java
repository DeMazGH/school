package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method - createFaculty");
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method - findFaculty");
        return facultyRepository.findById(id).orElse(null);
    }

    public Collection<Faculty> findAllFaculties() {
        logger.info("Was invoked method - findAllFaculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Was invoked method - findByColor");
        return facultyRepository.findFacultyByColor(color);
    }

    public Collection<Faculty> findFacultyByColorOrName(String nameOrColor) {
        logger.info("Was invoked method - findFacultyByColorOrName");
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method - updateFaculty");
        if (facultyRepository.findById(faculty.getId()).isPresent()) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void removeFaculty(Long id) {
        logger.info("Was invoked method - removeFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByFacultyId(long facultyId) {
        logger.info("Was invoked method - getStudentsByFacultyId");
        return studentRepository.findStudentByFacultyId(facultyId);
    }

    public String getLongestFacultyName() {
        logger.info("Was invoked method - getLongestFacultyName");
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
    }
}
