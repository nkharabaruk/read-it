package com.readit.integration.crud;

import com.readit.entity.Tag;
import com.readit.integration.client.TagClient;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class TagTest extends BaseTest<Tag> {

    @Autowired
    public void setClient(TagClient client) {
        this.client = client;
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Tag();
        entity1.setTitle("Книги 2017");
        entity1.setCount(7);

        entity2 = new Tag();
        entity2.setTitle("Казки");
        entity2.setCount(17);
    }
}