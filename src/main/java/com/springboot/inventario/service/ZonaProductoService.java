package com.springboot.inventario.service;

import com.springboot.inventario.entity.ZonaProducto;
import com.springboot.inventario.payload.ZonaProductoDto;

import java.util.List;

public interface ZonaProductoService {
    ZonaProductoDto AgregarProducto(ZonaProductoDto zonaProductoDto);

    ZonaProductoDto ObtenerZonaProducto(Long zonaId,Long productoId);

    List<ZonaProductoDto> obtenerProductosDeUnaZona(Long zonaId);

    ZonaProductoDto obtenerZonaProductoPorIdProducto(Long productoId);

    ZonaProductoDto ActualizarStock(ZonaProductoDto zonaProductoDto);   //update

    void EliminarProductoDeZona(Long zonaId,Long productoId);
}
