package com.readit.service;

import com.readit.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAll();

    Tag findById(long id);

    List<Tag> saveAll(List<Tag> list);

    Tag save(Tag tag);

    void deleteAll();

    void delete(long id);
}
