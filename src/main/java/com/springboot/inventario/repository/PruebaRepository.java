package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PruebaRepository extends JpaRepository<Prueba, UUID>{


}
