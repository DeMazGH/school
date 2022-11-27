package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private Long lastId = 0L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    public Collection<Faculty> findAllFaculties() {
        List<Faculty> facultyList = new ArrayList<>(faculties.values());
        return Collections.unmodifiableList(facultyList);
    }

    public Collection<Faculty> findByColor(String color) {
        return findAllFaculties().stream().filter(faculty -> faculty.getColor().equals(color)).toList();

    }

    public Faculty updateFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty removeFaculty(Long id) {
        return faculties.remove(id);
    }
}
