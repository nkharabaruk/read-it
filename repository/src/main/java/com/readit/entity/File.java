package com.readit.entity;

import com.readit.entity.enums.FileType;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"id"})
@Table(name = "FILE")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String path;

    @Enumerated(EnumType.STRING)
    private FileType type;
}
