package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.services.checkout.CheckoutInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebMvcTest(CheckoutController.class)
public class CheckoutControllerTest {
    // a fake client
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CheckoutInterface checkoutInterface;

    @Test
    public void testWrongInput() {
        Order o;
        Mockito.when(checkoutInterface.isValid(o.getPickUpAddress()))

    }


}