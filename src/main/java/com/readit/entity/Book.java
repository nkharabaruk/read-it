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
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String title;

    private String image;

    private Integer year;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> category;

    public Book(String title, Integer year, String description) {
        this.title = title;
        this.year = year;
        this.description = description;
    }
}
