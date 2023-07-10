package ru.hogwarts.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq",
            sequenceName = "student_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_seq")
    private Long id;
    private String name;
    private Integer age;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
