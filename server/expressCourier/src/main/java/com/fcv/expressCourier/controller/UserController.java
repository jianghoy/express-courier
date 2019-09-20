package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.dao.UserRepository;
import com.fcv.expressCourier.security.CurrentUser;
import com.fcv.expressCourier.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserData getCurrentUser(@CurrentUser UserPrincipal currentUser){
        return new UserData(currentUser.getName(),currentUser.getEmail());
    }

    private class UserData implements Serializable {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        String email;

        public UserData(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
}
