package com.springboot.inventario.service;

import com.springboot.inventario.entity.Producto;
import com.springboot.inventario.payload.ProductoDto;

import java.util.List;

public interface ProductoService {
    ProductoDto crearProducto(ProductoDto productoDto);

    ProductoDto obtenerProducto(Long id);

    ProductoDto ActualizarProducto(Long id,ProductoDto productoDto);

    void eliminarProducto(Long id);

    List<ProductoDto> EncontrarTodosLosProductosDeUnInventario(Long inventarioId);

}
