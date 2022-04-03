package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.entity.ZonaProducto;
import com.springboot.inventario.payload.ZonaProductoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZonaProductoRepository extends JpaRepository<ZonaProducto,Long> {

    @Query(
            value = "SELECT * FROM zona_producto WHERE " +
            "zona_producto.id_producto = ?2 AND zona_producto.id_zona = ?1" ,
            nativeQuery = true)
    ZonaProducto EncontrarProductoZona( Long zonaId, Long productoId);


    @Query(
            value = "SELECT zona_producto.* FROM zona_producto,zonas WHERE " +
                    "zona_producto.id_zona = ?1 ",
            nativeQuery = true
    )
    List<ZonaProducto> ProductosPorZonaId(Long zonaId);

    @Query(value = "SELECT zona_producto.* FROM zona_producto where zona_producto.id_producto = ?1",
    nativeQuery = true)
    ZonaProducto obtenerZonaProductoPorIdProducto(Long productoId);


}

