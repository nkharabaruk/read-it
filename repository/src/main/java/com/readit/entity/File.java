package com.readit.entity;

import com.readit.entity.enums.FileType;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "FILE")
public class File extends AbstractEntity {

    private String path;

    @Enumerated(EnumType.STRING)
    private FileType type;
}
