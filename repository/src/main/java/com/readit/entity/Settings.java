package com.readit.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SETTINGS")

public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean notificationAboutNewComment;
    private boolean notificationAboutNewBook;

    // others
}
