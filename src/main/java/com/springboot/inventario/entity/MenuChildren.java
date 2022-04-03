package com.springboot.inventario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menuChildren")
public class MenuChildren {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "title",nullable = false,length = 200)
    private String title;
    @Column(name = "component",nullable = false,columnDefinition = "TEXT")
    private String component;
    @Column(name = "path",nullable = false,columnDefinition = "TEXT")
    private String path;
    @Column(name = "icon",nullable = true,columnDefinition = "TEXT")
    private String icon;
    @Column(name = "estado",nullable = false)
    private Boolean estado;
    @Column(name = "orden",nullable = true)
    private int orden;
    @Column(name = "app",nullable = true,length = 2)
    private String app;
    @Column(name = "movil",nullable = true)
    private int movil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_menu", nullable = false)
    private Menu menu;

    @OneToOne(mappedBy = "menuChildren",cascade = CascadeType.ALL,orphanRemoval = true)
    private Permisos permisos;


}
