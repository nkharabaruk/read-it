package com.readit.integration.crud;

import com.readit.integration.client.CategoryClient;
import com.readit.rest.dto.CategoryDTO;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

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
        entity2.setParentId(entity1.getId());
    }
}