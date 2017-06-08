package com.readit.entity;

import com.readit.entity.enums.FileType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "FILE")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String path;

    @Enumerated(EnumType.STRING)
    private FileType type;
}
