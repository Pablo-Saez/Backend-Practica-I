package com.springboot.inventario.payload;

import com.springboot.inventario.entity.ZonaProducto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;
@Data
public class ProductoDto {
    private Long id;
    private String nombre;
    private String tipo;
    private String peso;
    private String SKU;


}
