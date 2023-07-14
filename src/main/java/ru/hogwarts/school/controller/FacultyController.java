package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyDataValidator;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;
    private final FacultyDataValidator facultyDataValidator;

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.createFaculty(faculty));
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable long facultyId) {
        return ResponseEntity.ok(facultyService.findFaculty(facultyId));
    }

    @GetMapping("/findByColor")
    public ResponseEntity<Collection<Faculty>> findFacultiesByColor(@RequestParam String nameOfColor) {
        return ResponseEntity.ok(facultyService.findByColor(nameOfColor));
    }

    @GetMapping("/findByColorOrName")
    public ResponseEntity<Collection<Faculty>> findFacultiesByColorOrName(@RequestParam String nameOrColor) {
        return ResponseEntity.ok(facultyService.findFacultyByColorOrName(nameOrColor));
    }

    @GetMapping("/{facultyId}/students")
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@PathVariable long facultyId) {
        facultyService.findFaculty(facultyId);
        return ResponseEntity.ok(facultyService.getStudentsByFacultyId(facultyId));
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        if (facultyDataValidator.facultyDataIsValid(faculty)) {
            return ResponseEntity.ok(facultyService.updateFaculty(faculty));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{facultyId}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable long facultyId) {
        facultyService.removeFaculty(facultyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getLongestFacultyName")
    public ResponseEntity<String> getLongestFacultyName() {
        return ResponseEntity.ok(facultyService.getLongestFacultyNameWithStream());
    }
}
