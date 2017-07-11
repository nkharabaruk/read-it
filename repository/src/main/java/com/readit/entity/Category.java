package com.readit.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString(exclude = {"parent"})
@EqualsAndHashCode(callSuper = false)
@Table(name = "CATEGORY")
public class Category extends AbstractEntity {

    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "BOOK_CATEGORY",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> books = new ArrayList<>();
}