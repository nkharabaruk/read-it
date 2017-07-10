package com.readit.service;

import com.readit.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAll();

    Tag findById(long id);

    Tag findByTitle(String title);

    Tag save(Tag tag);

    Tag update(long id, Tag tag);

    void deleteAll();

    void delete(long id);
}
