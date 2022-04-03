package com.springboot.inventario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.BasicPermission;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="perfiles_permisos")
public class Permisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "crud",nullable = false,length = 50)
    private String crud;
    @Column(name = "visible",nullable = false)
    private Boolean visible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_perfil")
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name="id_registeruser", nullable = false)
    private Registeruser registeruser;

    @ManyToOne //cambie la relacion
    @JoinColumn(name="id_menu")
    private Menu menu;

    @OneToOne
    @JoinColumn(name="id_permisos")
    private MenuChildren menuChildren;





}
