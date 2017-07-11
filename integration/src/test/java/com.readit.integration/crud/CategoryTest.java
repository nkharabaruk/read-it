package com.readit.integration.crud;

import com.readit.integration.client.CategoryClient;
import com.readit.rest.dto.CategoryDTO;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CategoryTest extends BaseTest<CategoryDTO> {

    @Autowired
    public void setClient(CategoryClient client) {
        this.client = client;
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new CategoryDTO();
        entity1.setName("Фантастика");

        entity2 = new CategoryDTO();
        entity2.setName("Українська");
        entity2.setChildren(Collections.singletonList(entity1));
    }
}