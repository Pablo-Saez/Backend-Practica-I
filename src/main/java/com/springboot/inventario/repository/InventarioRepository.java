package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Inventario;
import com.springboot.inventario.entity.Producto;
import com.springboot.inventario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario,Long> {
    List<Inventario> findByRegisteruserId(long registeruserId);


}
