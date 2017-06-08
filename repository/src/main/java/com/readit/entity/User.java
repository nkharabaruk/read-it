package com.readit.entity;

import com.readit.entity.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
    private File avatar;

    @OneToOne
    private Profile profile;
}
