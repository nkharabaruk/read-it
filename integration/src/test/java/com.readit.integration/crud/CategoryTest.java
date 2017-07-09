package com.readit.integration.crud;

import com.readit.entity.Category;
import com.readit.integration.client.CategoryClient;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CategoryTest extends BaseTest<Category> {

    @Autowired
    public void setClient(CategoryClient client) {
        this.client = client;
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Category();
        entity1.setName("Українська");
        entity1.setParent(null);

        entity2 = new Category();
        entity2.setName("Фантастика");
        entity2.setParent(entity1);
    }
}