package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void TestFind() {
        User u = new User();
        u.setEmail("j@j.com");
        u.setName("j");
        u.setPassword("123");
        userRepository.save(u);
        assertEquals("123",userRepository.findByNameOrEmail("j@j.com","j@j.com").get().getPassword());

    }

}