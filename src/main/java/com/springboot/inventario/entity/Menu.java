package com.springboot.inventario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",nullable = false,length = 200)
    private String title;
    @Column(name = "redirect",nullable = false,length = 200)
    private String redirect;
    @Column(name = "component",nullable = false,columnDefinition = "TEXT")
    private String component;
    @Column(name = "path",nullable = false,columnDefinition = "TEXT")
    private String path;
    @Column(name = "name",nullable = false,columnDefinition = "TEXT")
    private String name;
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

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Permisos> permisos;
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Menu> menu = new HashSet<>();
}
