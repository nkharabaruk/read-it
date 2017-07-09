package com.readit.integration.crud;

import com.readit.entity.Quote;
import com.readit.integration.client.QuoteClient;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class QuoteTest extends BaseTest<Quote> {

    @Autowired
    public void setClient(QuoteClient client) {
        this.client = client;
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Quote();
        entity1.setText("Мова завжди живе поряд з піснею, сестрою її рідною.");

        entity2 = new Quote();
        entity2.setText("Книги - морська глибина.");
    }

    @Override
    public void saveDuplicateTest() {
        // skip for now
    }
}