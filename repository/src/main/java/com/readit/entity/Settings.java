package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SETTINGS")

public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean notificationAboutNewComment;
    private boolean notificationAboutNewBook;

    // others
}
