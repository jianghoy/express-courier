package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Override
    <S extends Customer> S save(S s);

}
