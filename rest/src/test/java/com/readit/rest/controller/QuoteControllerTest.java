package com.readit.rest.controller;

import com.readit.entity.Quote;
import com.readit.rest.RestApplication;
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

        entity = new Quote();
        entity.setId(1L);
        entity.setBook(null);
        entity.setBackground(null);
        entity.setText("Мова завжди живе поряд з піснею, сестрою її рідною.");
    }
}