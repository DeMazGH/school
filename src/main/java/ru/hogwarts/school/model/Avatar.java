package ru.hogwarts.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {

    @Id
    @SequenceGenerator(name = "avatar_seq",
            sequenceName = "avatar_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "avatar_seq")
    private Long id;

    private String filePath;
    private long fileSize;
    private String mediaType;

    //@Lob
    @ToString.Exclude
    private byte[] data;

    @OneToOne
    private Student student;
}
