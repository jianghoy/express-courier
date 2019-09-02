package com.fcv.expressCourier.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    Optional<Address> findById(Integer id);
    Iterable<Address> findAllById(Iterable<Address> id);
    void addAddress(String city, String state, String zipcode, String country);
}
