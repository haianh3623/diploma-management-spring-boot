package com.globits.qlvbcc.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "diploma")
public class Diploma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String type;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School schoolDip;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
