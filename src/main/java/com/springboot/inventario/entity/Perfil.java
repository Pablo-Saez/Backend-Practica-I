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
@Table(name = "perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo",nullable = true)
    private int codigo;
    @Column(name = "nombre_perfil",nullable = false)
    private String nombre_perfil;
    @Column(name = "desc_perfil",nullable = false,length = 150)
    private String desc_perfil;
    @Column(name = "estado",nullable = false)
    private Boolean estado;
    @Column(name="visible",nullable = true)
    private Boolean visible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idregisteruser",nullable = false)
    private Registeruser registeruser;

    @OneToOne(mappedBy = "perfil",cascade = CascadeType.ALL,orphanRemoval = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Permisos> permisos = new HashSet<>();


}
