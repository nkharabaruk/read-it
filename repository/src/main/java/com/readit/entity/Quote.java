package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "QUOTE")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    private File background;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Book book;
}
