package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "QUOTE")
public class Quote extends AbstractEntity {

    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    private File background;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Book book;
}
