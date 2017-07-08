package com.readit.service.impl;

import com.readit.entity.Tag;
import com.readit.repository.TagRepository;
import com.readit.service.TagService;
import com.readit.service.exception.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(long id) {
        Tag tag = tagRepository.findOne(id);
        if (tag == null) throw new TagNotFoundException(id);
        return tag;
    }

    @Override
    public List<Tag> saveAll(List<Tag> list) {
        return tagRepository.save(list);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteAll() {
        tagRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            tagRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TagNotFoundException(id);
        }
    }
}
