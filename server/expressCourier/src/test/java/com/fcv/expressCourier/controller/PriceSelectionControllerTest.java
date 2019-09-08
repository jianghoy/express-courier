package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.priceCalculator.PriceCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceSelectionController.class)
public class PriceSelectionControllerTest {
    // a fake client
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceCalculator mockCalculator;

    @Test
    public void prices() throws Exception {
        /**
         * enforce mocking rules using Mockito
         */
        Mockito.when(mockCalculator.carPrice("SFO", "Dragon's gate"))
                .thenReturn((double) 10);
        Mockito.when(mockCalculator.dronePrice("SFO","Dragon's gate"))
                .thenReturn((double) 100);

        mockMvc
                .perform(
                        // request builder
                        get("/price")
                                .param("orig", "SFO")
                                .param("dest", "Dragon's gate")
                )
                //print output
                .andDo(print());
    }
}