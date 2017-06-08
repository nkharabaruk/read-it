package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    private String theme;
    private String text;

}
