package com.readit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = {"id"})
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Ignore
    private long id;

    private String firstName;
    private String lastName;
    private String middleName;

    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    private String biography;

    @OneToMany
    @JoinTable(name = "AUTHOR_FILE",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id"))
    private List<File> files;

    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> books;
}
