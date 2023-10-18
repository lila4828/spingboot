package com.example.mariadb.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class People {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
}
