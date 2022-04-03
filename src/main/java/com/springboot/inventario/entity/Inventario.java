package com.springboot.inventario.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo",nullable = false,length = 20)
    private String tipo;            //tipo de inventario guiado o normal
    @Column(name = "nombre",nullable = true)
    private String nombre;
    @Column(name = "ubicacion", nullable = true)
    private String ubicacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_registeruser", nullable = false)
    private Registeruser registeruser;

    @OneToMany(mappedBy = "inventario",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Zona> zonas=new HashSet<>();
}
