package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.Category;
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
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category1;
    private Category category2;
    private List<Category> categoties = new ArrayList<>();

    @Before
    public void setUp() {
        category1 = new Category();
        category1.setName("Category 1");

        category2 = new Category();
        category2.setName("Category 2");
        category2.setParent(category1);

        categoties.add(category1);
        categoties.add(category2);
    }

    @Test
    public void saveTest() {
        categoryRepository.save(category1);
        assertEquals(category1, categoryRepository.findOne(category1.getId()));
    }

    @Test
    public void saveIterableTest() {
        categoryRepository.save(categoties);
        assertTrue(categoryRepository.findAll().containsAll(categoties));
    }

    @Test
    public void existsTest() {
        categoryRepository.save(category1);
        assertTrue(categoryRepository.exists(category1.getId()));
    }

    @Test
    public void findOneTest() {
        categoryRepository.save(category1);
        assertEquals(category1, categoryRepository.findOne(category1.getId()));
    }

    @Test
    public void findAllTest() {
        categoryRepository.save(categoties);
        assertTrue(categoryRepository.findAll().containsAll(categoties));
    }

    @Test
    public void findAllByIdsTest() {
        categoryRepository.save(categoties);
        List<Long> ids = categoties.stream().map(Category::getId).collect(Collectors.toList());
        List<Category> foundCategories = categoryRepository.findAll(ids);
        assertEquals(categoties, foundCategories);

    }

    @Test
    public void countTest() {
        long before = categoryRepository.count();
        categoryRepository.save(categoties);
        assertEquals(before + categoties.size(), categoryRepository.count());
    }

    @Test
    public void deleteTest() {
        categoryRepository.save(category1);
        assertTrue(categoryRepository.exists(category1.getId()));
        categoryRepository.delete(category1);
        assertFalse(categoryRepository.exists(category1.getId()));
    }

    @Test
    public void deleteByIdTest() {
        categoryRepository.save(category1);
        assertTrue(categoryRepository.exists(category1.getId()));
        categoryRepository.delete(category1.getId());
        assertFalse(categoryRepository.exists(category1.getId()));
    }

    @Test
    public void deleteIterableTest() {
        categoryRepository.save(categoties);
        for (Category category : categoties) {
            assertTrue(categoryRepository.exists(category.getId()));
        }
        categoryRepository.delete(categoties);
        for (Category category : categoties) {
            assertFalse(categoryRepository.exists(category.getId()));
        }
    }

    @Test
    public void deleteAllTest() {
        categoryRepository.save(categoties);
        assertNotEquals(0, categoryRepository.count());
        categoryRepository.deleteAll();
        assertEquals(0, categoryRepository.count());
    }
}