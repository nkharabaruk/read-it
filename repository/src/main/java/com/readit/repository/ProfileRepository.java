package com.readit.repository;

import com.readit.entity.Book;
import com.readit.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findByWasReadAndIsReadingAndWantToRead(List<Book> wasRead, List<Book> isReading, List<Book>wantToTread);
}
