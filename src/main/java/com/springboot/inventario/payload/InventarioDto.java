package com.springboot.inventario.payload;

import com.springboot.inventario.entity.Registeruser;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class InventarioDto {
    private Long id;
    private String tipo;            //tipo de inventario guiado o normal
    private String nombre;
    private String ubicacion;
    private Long id_registeruser;
    //private Registeruser registeruser;
}
