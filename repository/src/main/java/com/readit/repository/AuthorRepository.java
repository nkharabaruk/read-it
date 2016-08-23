package com.readit.repository;

import com.readit.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByFirstNameContaining(String firstName);

    List<Author> findByMiddleNameContaining(String middleName);

    List<Author> findByLastNameContaining(String lastName);

}

