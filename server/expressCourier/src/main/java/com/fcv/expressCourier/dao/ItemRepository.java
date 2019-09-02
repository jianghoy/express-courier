package com.fcv.expressCourier.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    Optional<Item> findById(Integer id);
    void checkByWeight(Integer weight);
    void checkForPrice(Double prices);
    void checkByDimension(Integer length, Integer width, Integer height);
}
