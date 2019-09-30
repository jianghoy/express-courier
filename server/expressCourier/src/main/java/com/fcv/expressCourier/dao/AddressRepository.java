package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    @Override
    <S extends Address> S save(S s);
}
