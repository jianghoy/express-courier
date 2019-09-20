package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Role;
import com.fcv.expressCourier.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName roleName);
}
