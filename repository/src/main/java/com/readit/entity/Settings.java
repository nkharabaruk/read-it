package com.readit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "SETTINGS")
public class Settings extends AbstractEntity {

    private boolean notificationAboutNewComment;
    private boolean notificationAboutNewBook;

    // others
}
