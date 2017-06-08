package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    // size of Tag
    private Integer count;
}
