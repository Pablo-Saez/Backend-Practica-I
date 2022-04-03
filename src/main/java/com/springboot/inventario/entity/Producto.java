package com.springboot.inventario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @Column(name="peso")
    private String peso;
    @Column(name = "SKU",nullable = false)
    private String SKU;

    @OneToMany(mappedBy = "producto")
    private Set<ZonaProducto> zonaProducto;
}


