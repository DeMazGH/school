package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Collection<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findFacultyByColor(color);
    }

    public Collection<Faculty> findFacultyByColorOrName(String nameOrColor) {
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (facultyRepository.findById(faculty.getId()).isPresent()) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void removeFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByFacultyId(long facultyId) {
        return studentRepository.findStudentByFacultyId(facultyId);
    }
}
