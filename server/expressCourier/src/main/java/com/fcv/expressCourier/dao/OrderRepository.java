package com.fcv.expressCourier.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    <S extends Order> S save(S s);
    Optional<Order> findById(Integer id);
    void addPrice(Integer price);
    void findStatusInfo(String status);
    boolean addOrderType(Boolean orderType);
    List<Item> findByOrderId(Integer id);
}
