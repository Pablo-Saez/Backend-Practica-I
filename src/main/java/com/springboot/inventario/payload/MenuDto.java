package com.springboot.inventario.payload;

import lombok.Data;

import javax.persistence.Column;
@Data
public class MenuDto {
    private Long id;
    private String title;
    private String redirect;
    private String component;
    private String path;
    private String name;
    private String icon;
    private Boolean estado;
    private int orden;
    private String app;
    private int movil;

}
