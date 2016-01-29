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

    private String name;

    private Integer dateOfBirth;

    private Integer dateOfDeath;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;

    private String biography;

    public Author(String name, Integer dateOfBirth, Integer dateOfDeath, List<Book> book, String biography) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.books = book;
        this.biography = biography;
    }
}
