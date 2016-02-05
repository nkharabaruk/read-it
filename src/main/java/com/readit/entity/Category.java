package com.readit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(cascade={CascadeType.ALL})
    private Category parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Category> children;
}
