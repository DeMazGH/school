package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable long facultyId) {
        Faculty faculty = facultyService.findFaculty(facultyId);
        if (faculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping()
    public ResponseEntity<Collection<Faculty>> findFacultiesByColor(@RequestParam(required = false) String color,
                                                                    @RequestParam(required = false) String name) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByName(name));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/getStudentsByFaculty/{facultyId}")
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@PathVariable long facultyId) {
        if (facultyService.findFaculty(facultyId) == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.getStudentsByFaculty(facultyId));
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable long facultyId) {
        facultyService.removeFaculty(facultyId);
        return ResponseEntity.ok().build();
    }
}
