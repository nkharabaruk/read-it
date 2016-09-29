package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.File;
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
public class FileRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    private File file1;
    private File file2;
    private List<File> files = new ArrayList<>();

    @Before
    public void setUp() {
        file1 = new File();
        file1.setPath("/path/to/file1");

        file2 = new File();
        file2.setPath("/path/to/file2");

        files.add(file1);
        files.add(file2);
    }

    @Test
    public void saveTest() {
        imageRepository.save(file1);
        assertEquals(file1, imageRepository.findOne(file1.getId()));
    }

    @Test
    public void saveIterableTest() {
        imageRepository.save(files);
        assertTrue(imageRepository.findAll().containsAll(files));
    }

    @Test
    public void existsTest() {
        imageRepository.save(file1);
        assertTrue(imageRepository.exists(file1.getId()));
    }

    @Test
    public void findOneTest() {
        imageRepository.save(file1);
        assertEquals(file1, imageRepository.findOne(file1.getId()));
    }

    @Test
    public void findAllTest() {
        imageRepository.save(files);
        assertTrue(imageRepository.findAll().containsAll(files));
    }

    @Test
    public void findAllByIdsTest() {
        imageRepository.save(files);
        List<Long> ids = files.stream().map(File::getId).collect(Collectors.toList());
        List<File> foundFiles = imageRepository.findAll(ids);
        assertEquals(files, foundFiles);

    }

    @Test
    public void countTest() {
        long before = imageRepository.count();
        imageRepository.save(files);
        assertEquals(before + files.size(), imageRepository.count());
    }

    @Test
    public void deleteTest() {
        imageRepository.save(file1);
        assertTrue(imageRepository.exists(file1.getId()));
        imageRepository.delete(file1);
        assertFalse(imageRepository.exists(file1.getId()));
    }

    @Test
    public void deleteByIdTest() {
        imageRepository.save(file1);
        assertTrue(imageRepository.exists(file1.getId()));
        imageRepository.delete(file1.getId());
        assertFalse(imageRepository.exists(file1.getId()));
    }

    @Test
    public void deleteIterableTest() {
        imageRepository.save(files);
        for (File file : files) {
            assertTrue(imageRepository.exists(file.getId()));
        }
        imageRepository.delete(files);
        for (File file : files) {
            assertFalse(imageRepository.exists(file.getId()));
        }
    }

    @Test
    public void deleteAllTest() {
        imageRepository.save(files);
        assertNotEquals(0, imageRepository.count());
        imageRepository.deleteAll();
        assertEquals(0, imageRepository.count());
    }

}