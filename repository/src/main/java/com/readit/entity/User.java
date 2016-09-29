package com.readit.entity;

import com.readit.entity.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;

/**
 * Created by nataliia on 28.09.16.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;

    private String firstName;
    private String lastName;
    private Calendar dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY)
    private File avatar;

    @OneToOne(fetch = FetchType.LAZY)
    private Profile profile;
}
