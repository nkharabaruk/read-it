package com.readit.integration.crud;

import com.readit.entity.Book;
import com.readit.integration.client.BookClient;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Year;

public class BookTest extends BaseTest<Book> {

    @Autowired
    public void setClient(BookClient client) {
        this.client = client;
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Book();
        entity1.setTitle("Кобзар");
        entity1.setDescription("Збірка поетичних творів Тараса Григоровича Шевченка");
        entity1.setYearOfRelease(Year.of(1840));

        entity2 = new Book();
        entity2.setTitle("Фарбований лис");
        entity2.setDescription("Казка для дітей про Лисовичі");
        entity2.setYearOfRelease(Year.of(1890));
    }
}