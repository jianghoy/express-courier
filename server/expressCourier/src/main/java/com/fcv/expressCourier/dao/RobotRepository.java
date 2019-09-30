package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RobotRepository extends JpaRepository<Robot,Integer> {

    @Override
    <S extends Robot> S save(S s);

    List<Robot> findAllByType(int type);

}
