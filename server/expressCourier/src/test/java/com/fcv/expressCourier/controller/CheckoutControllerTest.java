package com.fcv.expressCourier.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fcv.expressCourier.models.Address;
import com.fcv.expressCourier.models.Order;
import com.fcv.expressCourier.services.deliveryManagement.CheckoutInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(CheckoutController.class)
public class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckoutInterface checkoutInterface;

    @Test
    public void testCheckout() {
        Order o = new Order();
        Address shippingAddress = new Address();
        shippingAddress.setState("CA");
        shippingAddress.setCity("San Francisco");
        shippingAddress.setAddress("140 Mongomery");
        o.setShippingAddress(shippingAddress);
        Address pickupAddress = new Address();
        pickupAddress.setAddress("1730 Mt Rainier Ave");
        pickupAddress.setCity("Milpitas");
        pickupAddress.setState("CA");
        o.setPickUpAddress(pickupAddress);

        Mockito.when(checkoutInterface.isValid(o)).thenReturn(false);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String requestJson = ow.writeValueAsString(o);

            mockMvc.perform(
                    put("/checkout/").contentType(APPLICATION_JSON_UTF8)
                            .content(requestJson)).andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


}