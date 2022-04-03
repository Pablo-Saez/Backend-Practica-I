package com.springboot.inventario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="usuarios")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false,length = 150)
    private String nombre;
    @Column(name = "apellido",nullable = false,length = 150)
    private String apellido;
    @Column(name = "email",nullable = false,length = 150)
    private String email;
    @Column(name = "password",nullable = false,length = 150)
    private String password;
    @Column(name = "rut",nullable = true,length = 50)
    private String rut;
    @Column(name = "idkey",nullable = true,length = 150)
    private String idkey;
    @Column(name = "validatekey",nullable = true,length = 150)
    private String validatekey;
    @Column(name = "lastaccessdate",nullable = true)
    private Date lastaccessdate;
    @Column(name = "validate",nullable = true)
    private int validate;
    @Column(name = "active", nullable = true)
    private Boolean active;
    @Column(name = "parametros",nullable = true,columnDefinition = "TEXT")
    private String parametros;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_registeruser", nullable = false)
    private Registeruser registeruser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idperfil",nullable = true)
    private Perfil perfil;
}
