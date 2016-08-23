package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.Image;
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
public class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    private Image image1;
    private Image image2;
    private List<Image> images = new ArrayList<>();

    @Before
    public void setUp() {
        image1 = new Image();
        image1.setPath("/path/to/image1");

        image2 = new Image();
        image2.setPath("/path/to/image2");

        images.add(image1);
        images.add(image2);
    }

    @Test
    public void saveTest() {
        imageRepository.save(image1);
        assertEquals(image1, imageRepository.findOne(image1.getId()));
    }

    @Test
    public void saveIterableTest() {
        imageRepository.save(images);
        assertTrue(imageRepository.findAll().containsAll(images));
    }

    @Test
    public void existsTest() {
        imageRepository.save(image1);
        assertTrue(imageRepository.exists(image1.getId()));
    }

    @Test
    public void findOneTest() {
        imageRepository.save(image1);
        assertEquals(image1, imageRepository.findOne(image1.getId()));
    }

    @Test
    public void findAllTest() {
        imageRepository.save(images);
        assertTrue(imageRepository.findAll().containsAll(images));
    }

    @Test
    public void findAllByIdsTest() {
        imageRepository.save(images);
        List<Long> ids = images.stream().map(Image::getId).collect(Collectors.toList());
        List<Image> foundImages = imageRepository.findAll(ids);
        assertEquals(images, foundImages);

    }

    @Test
    public void countTest() {
        long before = imageRepository.count();
        imageRepository.save(images);
        assertEquals(before + images.size(), imageRepository.count());
    }

    @Test
    public void deleteTest() {
        imageRepository.save(image1);
        assertTrue(imageRepository.exists(image1.getId()));
        imageRepository.delete(image1);
        assertFalse(imageRepository.exists(image1.getId()));
    }

    @Test
    public void deleteByIdTest() {
        imageRepository.save(image1);
        assertTrue(imageRepository.exists(image1.getId()));
        imageRepository.delete(image1.getId());
        assertFalse(imageRepository.exists(image1.getId()));
    }

    @Test
    public void deleteIterableTest() {
        imageRepository.save(images);
        for (Image image : images) {
            assertTrue(imageRepository.exists(image.getId()));
        }
        imageRepository.delete(images);
        for (Image image : images) {
            assertFalse(imageRepository.exists(image.getId()));
        }
    }

    @Test
    public void deleteAllTest() {
        imageRepository.save(images);
        assertNotEquals(0, imageRepository.count());
        imageRepository.deleteAll();
        assertEquals(0, imageRepository.count());
    }

}