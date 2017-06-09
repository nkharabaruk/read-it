package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    private String theme;
    private String text;

}
