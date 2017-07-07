package com.readit.rest.controller;

import com.readit.RestApplication;
import com.readit.entity.Quote;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuoteControllerTest extends AbstractControllerTest<Quote> {

    @Override
    protected String getURL() {
        return "/rest/quotes";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Quote();
        entity1.setText("Мова завжди живе поряд з піснею, сестрою її рідною.");

        entity2 = new Quote();
        entity2.setText("Книги - морська глибина.");
    }
}