package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "SETTINGS")
public class Settings extends AbstractEntity {

    private boolean notificationAboutNewComment;
    private boolean notificationAboutNewBook;

    // others
}
