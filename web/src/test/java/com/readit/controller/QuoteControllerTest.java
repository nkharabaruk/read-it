package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Quote;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuoteControllerTest extends AbstractControllerTest<Quote> {

    public QuoteControllerTest() {
        super();
    }

    @Override
    protected String getURL() {
        return "/quotes";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new Quote();

        // assume that db is empty
        entity.setId(1L);
        entity.setBook(null);
        entity.setBackground(null);
        entity.setText("Мова завжди живе поряд з піснею, сестрою її рідною.");
    }
}