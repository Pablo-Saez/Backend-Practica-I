package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
