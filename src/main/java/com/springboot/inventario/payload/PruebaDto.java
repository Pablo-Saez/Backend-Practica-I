package com.springboot.inventario.payload;

import lombok.Data;

import java.util.UUID;
@Data
public class PruebaDto {
    private UUID id;
    private String username;
    private String last_name;
    private int valor;
    private String email;
}
