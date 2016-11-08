package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.File;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest(classes = RepositoryApplication.class)
public class FileRepositoryTest {

    @Autowired
    private FileRepository fileRepository;

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
        fileRepository.save(file1);
        assertEquals(file1, fileRepository.findOne(file1.getId()));
    }

    @Test
    public void saveIterableTest() {
        fileRepository.save(files);
        assertTrue(fileRepository.findAll().containsAll(files));
    }

    @Test
    public void existsTest() {
        fileRepository.save(file1);
        assertTrue(fileRepository.exists(file1.getId()));
    }

    @Test
    public void findOneTest() {
        fileRepository.save(file1);
        assertEquals(file1, fileRepository.findOne(file1.getId()));
    }

    @Test
    public void findAllTest() {
        fileRepository.save(files);
        assertTrue(fileRepository.findAll().containsAll(files));
    }

    @Test
    public void findAllByIdsTest() {
        fileRepository.save(files);
        List<Long> ids = files.stream().map(File::getId).collect(Collectors.toList());
        List<File> foundFiles = fileRepository.findAll(ids);
        assertEquals(files, foundFiles);

    }

    @Test
    public void countTest() {
        long before = fileRepository.count();
        fileRepository.save(files);
        assertEquals(before + files.size(), fileRepository.count());
    }

    @Test
    public void deleteTest() {
        fileRepository.save(file1);
        assertTrue(fileRepository.exists(file1.getId()));
        fileRepository.delete(file1);
        assertFalse(fileRepository.exists(file1.getId()));
    }

    @Test
    public void deleteByIdTest() {
        fileRepository.save(file1);
        assertTrue(fileRepository.exists(file1.getId()));
        fileRepository.delete(file1.getId());
        assertFalse(fileRepository.exists(file1.getId()));
    }

    @Test
    public void deleteIterableTest() {
        fileRepository.save(files);
        for (File file : files) {
            assertTrue(fileRepository.exists(file.getId()));
        }
        fileRepository.delete(files);
        for (File file : files) {
            assertFalse(fileRepository.exists(file.getId()));
        }
    }

    @Test
    public void deleteAllTest() {
        fileRepository.save(files);
        assertNotEquals(0, fileRepository.count());
        fileRepository.deleteAll();
        assertEquals(0, fileRepository.count());
    }

}