package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Drone;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DroneRepository extends CrudRepository<Drone, Long> {

    @Override
    <S extends Drone> S save(S s);

    @Override
    Optional<Drone> findById(Long aLong);
}
