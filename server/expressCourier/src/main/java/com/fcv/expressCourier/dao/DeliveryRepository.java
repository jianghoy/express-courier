package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Delivery;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    @Override
    <S extends Delivery> S save(S s);

    @Override
    Optional<Delivery> findById(Long aLong);
}
