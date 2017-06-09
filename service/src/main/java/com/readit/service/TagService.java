package com.readit.service;

import com.readit.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagService {

    List<Tag> findAll();

    Tag findById(long id);

    List<Tag> saveAll(List<Tag> list);

    Tag save(Tag tag);

    void deleteAll();

    void delete(Tag tag);
}
