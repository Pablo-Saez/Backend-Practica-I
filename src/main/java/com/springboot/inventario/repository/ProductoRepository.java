package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    @Query(value = "SELECT productos.* FROM productos,zonas,zona_producto WHERE productos.id=zona_producto.id_producto AND zona_producto.id_zona = zonas.id\n" +
            "AND zonas.id_inventario= ?1",
            nativeQuery = true)
    List<Producto> EncontrarTodosLosProductosDeUnInventario(Long inventarioId);
}
