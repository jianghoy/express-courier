package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Delivery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeliveryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void testDeliveryCRUD() {
        Delivery delivery = new Delivery((long) 0, "deliverd");
        testEntityManager.persist(delivery);
        testEntityManager.flush();
        Optional<Delivery> found = deliveryRepository.findById((long) 0);
        // found is Optional class; found.get() is Delivery class
        assertEquals(found.get(), delivery);
    }

}