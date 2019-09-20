package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Customer;
import com.fcv.expressCourier.models.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Override
    Optional<Order> findById(Integer integer);

    @Override
    <S extends Order> S save(S s);

    @Override
    List<Order> findAll();

    Slice<Order> findAllByCustomer(Customer customer, Pageable pageable);
}
