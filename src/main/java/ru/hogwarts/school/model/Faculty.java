package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {

    @Id
    @SequenceGenerator(name = "faculty_seq",
            sequenceName = "faculty_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "faculty_seq")
    private Long id;

    private String name;
    private String color;

    @JsonIgnore
    @OneToMany(mappedBy = "faculty")
    private List<Student> students;
}
