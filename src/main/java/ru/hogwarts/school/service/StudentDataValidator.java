package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

@Service
public class StudentDataValidator {

    public boolean studentDataIsValid(Student student) {
        return student != null && student.getId() != null;
    }
}
