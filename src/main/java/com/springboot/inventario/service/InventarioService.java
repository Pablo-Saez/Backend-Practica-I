package com.springboot.inventario.service;

import com.springboot.inventario.entity.Producto;
import com.springboot.inventario.payload.InventarioDto;
import com.springboot.inventario.payload.ProductoDto;

import java.util.List;

public interface InventarioService {

     InventarioDto createInventario(Long id,InventarioDto inventarioDto);

     List<InventarioDto> obtenerInventariosConRegisteruserId(long id);

     List<InventarioDto> obtenerTodosLosInventariosSinId();

     InventarioDto actualizarInventario(Long id,InventarioDto inventarioDto);


     void deleteInventario(Long id);
}
