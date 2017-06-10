package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "TAG")
public class Tag extends AbstractEntity {

    private String title;

    // size of Tag
    private Integer count;
}
