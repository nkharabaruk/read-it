package com.readit.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
    private Collection<File> file;

    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Collection<Book> books;
}
