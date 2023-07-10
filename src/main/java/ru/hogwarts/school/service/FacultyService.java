package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Comparator;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public Faculty createFaculty(Faculty faculty) {
        log.debug("Was invoked method - createFaculty");
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        log.debug("Was invoked method - findFaculty");
        return facultyRepository.findById(id).orElse(null);
    }

    public Collection<Faculty> findAllFaculties() {
        log.debug("Was invoked method - findAllFaculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        log.debug("Was invoked method - findByColor");
        return facultyRepository.findFacultyByColor(color);
    }

    public Collection<Faculty> findFacultyByColorOrName(String nameOrColor) {
        log.debug("Was invoked method - findFacultyByColorOrName");
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    public Faculty updateFaculty(Faculty faculty) {
        log.debug("Was invoked method - updateFaculty");
        if (facultyRepository.findById(faculty.getId()).isPresent()) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void removeFaculty(Long id) {
        log.debug("Was invoked method - removeFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByFacultyId(long facultyId) {
        log.debug("Was invoked method - getStudentsByFacultyId");
        return studentRepository.findStudentByFacultyId(facultyId);
    }

    public String getLongestFacultyName() {
        log.debug("Was invoked method - getLongestFacultyName");
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
    }
}
