package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    // size of Tag
    private Integer count;
}
