package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Usuario;
import com.springboot.inventario.entity.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZonaRepository extends JpaRepository<Zona,Long> {
    List<Zona> findByInventarioId(long inventarioId);


}
