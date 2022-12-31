package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

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

    @GetMapping("/{facultyId}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable long facultyId) {
        Faculty faculty = facultyService.findFaculty(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/findByColor")
    public ResponseEntity<Collection<Faculty>> findFacultiesByColor(@RequestParam String color) {
        return ResponseEntity.ok(facultyService.findByColor(color));
    }

    @GetMapping("/findByColorOrName")
    public ResponseEntity<Collection<Faculty>> findFacultiesByColorOrName(@RequestParam String nameOrColor) {
        return ResponseEntity.ok(facultyService.findFacultyByColorOrName(nameOrColor));
    }

    @GetMapping("/{facultyId}/students")
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@PathVariable long facultyId) {
        if (facultyService.findFaculty(facultyId) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.getStudentsByFacultyId(facultyId));
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{facultyId}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable long facultyId) {
        facultyService.removeFaculty(facultyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getLongestFacultyName")
    public ResponseEntity<String> getLongestFacultyName() {
        return ResponseEntity.ok(facultyService.getLongestFacultyName());
    }
}
