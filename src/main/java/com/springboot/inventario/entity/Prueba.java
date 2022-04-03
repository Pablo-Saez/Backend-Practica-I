package com.springboot.inventario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

//ENTIDAD PARA HACER PRUEBAS

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prueba")
public class Prueba {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String last_name;
    private int valor;
    private String email;
}
