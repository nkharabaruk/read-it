package com.readit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String image;

    private Integer dateOfBirth;

    private Integer dateOfDeath;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;

    private String biography;

    public Author(String firstName, String lastName, String middleName, Integer dateOfBirth, Integer dateOfDeath, List<Book> book, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.books = book;
        this.biography = biography;
    }
}
