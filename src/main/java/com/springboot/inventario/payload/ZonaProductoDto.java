package com.springboot.inventario.payload;

import com.springboot.inventario.entity.Producto;
import com.springboot.inventario.entity.Zona;
import lombok.Data;



@Data
public class ZonaProductoDto {
    private Long id;
    private int stock;
    private Long zona_id;
    private Long producto_id;

}
