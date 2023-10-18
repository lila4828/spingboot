package com.example.mariadb.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString()
@Table(name = "student_table")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  //이름

    @Column(nullable = false)
    private String dept;  //학과

    @Column(nullable = false)
    private String year;  //학년

    @Column(nullable = false, columnDefinition = "float default 0.0")
    private float gpa;   //학점

    @Column(columnDefinition = "integer default 0")
    private int age;     //나이

    @Column
    private String email;   //이메일
}