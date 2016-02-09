package com.readit.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"authors", "categories"})
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String title;

    private String image;

    private Integer year;

    private String description;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<Author> authors;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<Category> categories;

    public Book(String title, Integer year, String description) {
        this.title = title;
        this.year = year;
        this.description = description;
    }
}
