package com.readit.service.impl;

import com.readit.entity.Tag;
import com.readit.repository.TagRepository;
import com.readit.service.TagService;
import com.readit.service.exception.TagAlreadyExistsException;
import com.readit.service.exception.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
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
        return tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id));
    }

    @Override
    public Tag findByTitle(String title) {
        Tag tag = tagRepository.findByTitle(title);
        if (tag == null) throw new TagNotFoundException("title", title);
        return tag;
    }

    @Override
    public Tag save(Tag tag) {
        Tag existing = tagRepository.findByTitle(tag.getTitle());
        if (existing != null) throw new TagAlreadyExistsException(existing);
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(long id, Tag tag) {
        try {
            Tag existing = tagRepository.getOne(id);
            tag.setId(existing.getId());
            return tagRepository.save(tag);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new TagNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        tagRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            tagRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TagNotFoundException(id);
        }
    }
}
