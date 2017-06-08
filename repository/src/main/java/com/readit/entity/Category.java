package com.readit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;

    @ManyToMany
    @JoinTable(name = "BOOK_CATEGORY",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> books;
}