package com.readit.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Category parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private Collection<Category> children;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "BOOK_CATEGORY",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Collection<Book> books;
}