package com.fcv.expressCourier.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceSelectionController.class)
public class PriceSelectionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void prices() throws Exception {
        mockMvc
                .perform(
                        get("/price")
                                .param("orig", "SFO")
                                .param("dest", "Dragon's gate")
                )
                .andDo(print());
    }
}