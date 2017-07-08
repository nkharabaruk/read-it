package com.readit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.Year;
import java.util.List;

@Data
@Entity
@ToString(exclude = {"authors", "categories", "tags"})
@EqualsAndHashCode(callSuper = false, exclude = {"authors", "categories", "tags"})
@Table(name = "BOOK")
public class Book extends AbstractEntity {

    private String title;
    private Year yearOfRelease;
    private String description;

    @OneToOne
    private File cover;

    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "BOOK_CATEGORY",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "BOOK_TAG",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;
}