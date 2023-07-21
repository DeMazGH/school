package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

@Service
@RequiredArgsConstructor
public class FacultyDataValidator {

    public boolean facultyDataIsValid(Faculty faculty) {
        return faculty != null && faculty.getId() != null;
    }
}
