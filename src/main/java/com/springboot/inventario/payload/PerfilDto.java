package com.springboot.inventario.payload;

import lombok.Data;

@Data
public class PerfilDto {
    private Long id;
    private int codigo;
    private String nombre_perfil;
    private String desc_perfil;
    private Boolean estado;
    private Boolean visible;
    private Long idRegisteruser;
}
