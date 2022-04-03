package com.springboot.inventario.payload;

import com.springboot.inventario.entity.Perfil;
import com.springboot.inventario.entity.Registeruser;
import lombok.Data;

@Data
public class PermisosDto {
    private Long id;
    private String crud;
    private Boolean visible;
    private Long id_registeruser;
    private Long id_perfil;
    private Long id_menu;
    private Long id_menuChildren;
}
