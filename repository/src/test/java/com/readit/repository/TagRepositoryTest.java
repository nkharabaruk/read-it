package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.Tag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryApplication.class)
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    private Tag tag1;
    private Tag tag2;
    private List<Tag> tags = new ArrayList<>();

    @Before
    public void setUp() {
        tag1 = new Tag();
        tag1.setTitle("Tag 1");

        tag2 = new Tag();
        tag2.setTitle("Tag 2");

        tags.add(tag1);
        tags.add(tag2);
    }

    @Test
    public void saveTest() {
        tagRepository.save(tag1);
        assertEquals(tag1, tagRepository.findOne(tag1.getId()));
    }

    @Test
    public void saveIterableTest() {
        tagRepository.save(tags);
        assertTrue(tagRepository.findAll().containsAll(tags));
    }

    @Test
    public void existsTest() {
        tagRepository.save(tag1);
        assertTrue(tagRepository.exists(tag1.getId()));
    }

    @Test
    public void findOneTest() {
        tagRepository.save(tag1);
        assertEquals(tag1, tagRepository.findOne(tag1.getId()));
    }

    @Test
    public void findAllTest() {
        tagRepository.save(tags);
        assertTrue(tagRepository.findAll().containsAll(tags));
    }

    @Test
    public void findAllByIdsTest() {
        tagRepository.save(tags);
        List<Long> ids = tags.stream().map(Tag::getId).collect(Collectors.toList());
        List<Tag> foundTags = tagRepository.findAll(ids);
        assertEquals(tags, foundTags);

    }

    @Test
    public void countTest() {
        long before = tagRepository.count();
        tagRepository.save(tags);
        assertEquals(before + tags.size(), tagRepository.count());
    }

    @Test
    public void deleteTest() {
        tagRepository.save(tag1);
        assertTrue(tagRepository.exists(tag1.getId()));
        tagRepository.delete(tag1);
        assertFalse(tagRepository.exists(tag1.getId()));
    }

    @Test
    public void deleteByIdTest() {
        tagRepository.save(tag1);
        assertTrue(tagRepository.exists(tag1.getId()));
        tagRepository.delete(tag1.getId());
        assertFalse(tagRepository.exists(tag1.getId()));
    }

    @Test
    public void deleteIterableTest() {
        tagRepository.save(tags);
        for (Tag tag : tags) {
            assertTrue(tagRepository.exists(tag.getId()));
        }
        tagRepository.delete(tags);
        for (Tag tag : tags) {
            assertFalse(tagRepository.exists(tag.getId()));
        }
    }

    @Test
    public void deleteAllTest() {
        tagRepository.save(tags);
        assertNotEquals(0, tagRepository.count());
        tagRepository.deleteAll();
        assertEquals(0, tagRepository.count());
    }

}