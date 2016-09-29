package com.readit.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by nataliia on 28.09.16.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "PROFILE")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PROFILE_WANT_TO_READ",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "want_to_read_id", referencedColumnName = "id"))
    private Collection<Book> wantToRead;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PROFILE_IS_READIND",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "is_reading_id", referencedColumnName = "id"))
    private Collection<Book> isReading;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PROFILE_WAS_READ",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "was_read_id", referencedColumnName = "id"))
    private Collection<Book> wasRead;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Settings settings;
}
