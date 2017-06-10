package com.readit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "PROFILE")
public class Profile extends AbstractEntity {

    @OneToMany
    @JoinTable(name = "PROFILE_WANT_TO_READ",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "want_to_read_id", referencedColumnName = "id"))
    private List<Book> wantToRead;

    @OneToMany
    @JoinTable(name = "PROFILE_IS_READING",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "is_reading_id", referencedColumnName = "id"))
    private List<Book> isReading;

    @OneToMany
    @JoinTable(name = "PROFILE_WAS_READ",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "was_read_id", referencedColumnName = "id"))
    private List<Book> wasRead;

    @OneToOne(cascade = {CascadeType.ALL})
    private Settings settings;
}
