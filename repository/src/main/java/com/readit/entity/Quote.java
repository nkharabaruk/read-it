package com.readit.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by nataliia on 29.09.16.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "QUOTE")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    private File background;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "QUOTE_AUTHOR",
            joinColumns = @JoinColumn(name = "quote_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Collection<Author> authors;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Book book;
}
