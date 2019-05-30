package com.readit.integration.crud;

import com.readit.entity.Author;
import com.readit.integration.client.AuthorClient;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

public class AuthorTest extends BaseTest<Author> {

    @Autowired
    public void setClient(AuthorClient client) {
        this.client = client;
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Author();
        entity1.setFirstName("Тарас");
        entity1.setMiddleName("Григорович");
        entity1.setLastName("Шевченко");
        entity1.setDateOfBirth(LocalDate.of(1814, 3, 9));
        entity1.setDateOfDeath(LocalDate.of(1861, 3, 10));
        entity1.setBiography("Народився в селі Моринці");

        entity2 = new Author();
        entity2.setFirstName("Іван");
        entity2.setMiddleName("Якович");
        entity2.setLastName("Франко");
        entity2.setDateOfBirth(LocalDate.of(1856, 8, 27));
        entity2.setDateOfDeath(LocalDate.of(1916, 5, 28));
        entity2.setBiography("Народився в селі Нагуєвичі");
    }

}
