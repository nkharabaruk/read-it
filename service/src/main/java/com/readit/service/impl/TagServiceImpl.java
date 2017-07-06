package com.readit.service.impl;

import com.readit.entity.Tag;
import com.readit.repository.TagRepository;
import com.readit.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return tagRepository.findOne(id);
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
        tagRepository.delete(id);
    }
}
