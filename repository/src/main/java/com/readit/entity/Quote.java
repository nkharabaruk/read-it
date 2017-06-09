package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "QUOTE")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    private File background;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Book book;
}
