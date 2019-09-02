package com.fcv.expressCourier.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findById(Integer id);
    void addName(String name);
    void addPhone(String phoneNum);
    void addAddress(String address);
    User getUserById(Integer id);
}
