package com.springboot.inventario.payload;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MenuChildrenDto {
    private Long id;
    private String title;
    private String component;
    private String path;
    private String icon;
    private Boolean estado;
    private int orden;
    private String app;
    private int movil;
    private Long id_menu;
}
