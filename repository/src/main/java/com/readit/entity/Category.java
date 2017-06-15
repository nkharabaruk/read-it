package com.readit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

@Lazy
@Data
@Entity
@ToString(exclude = {"parent","children","books"})
@EqualsAndHashCode(callSuper = false, exclude = {"books", "parent", "children"})
@Table(name = "CATEGORY")
public class Category extends AbstractEntity {

    private String name;

    @JsonIgnore
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