package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.dao.OrderRepository;
import com.fcv.expressCourier.models.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


//@RunWith(SpringRunner.class)
//@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository mockOrderRepository;

    @Test
    public void getOrder() {
        Order order = new Order();
        order.setId(1);
        Mockito.when(mockOrderRepository.findById(1)).thenReturn(Optional.of(order));
        Mockito.when(mockOrderRepository.findById(2)).thenReturn(Optional.empty());


        try {
            mockMvc.perform(
                    get("/order/1")
            ).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(
                    get("/order/2")
            ).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}