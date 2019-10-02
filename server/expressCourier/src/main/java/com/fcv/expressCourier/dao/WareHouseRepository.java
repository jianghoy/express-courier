package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.WareHouse;
import org.springframework.data.repository.CrudRepository;

public interface WareHouseRepository extends CrudRepository<WareHouse, Integer> {

    @Override
    <S extends WareHouse> S save(S s);

    @Override
    Iterable<WareHouse> findAll();
}
