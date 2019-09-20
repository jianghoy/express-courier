package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User getUserById(int id);

}
