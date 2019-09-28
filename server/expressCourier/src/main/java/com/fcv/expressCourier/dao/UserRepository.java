package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findById(int id);
    Optional<User> findByNameOrEmail(String name, String email);
    boolean existsByName(String name);
    boolean existsByEmail(String email);

    @Override
    <S extends User> S save(S s);
}
