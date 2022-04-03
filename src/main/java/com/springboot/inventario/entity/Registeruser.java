package com.springboot.inventario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registeruser",uniqueConstraints = {@UniqueConstraint(columnNames={"email"})})
public class Registeruser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable = false,length = 150)
    private String name;
    @Column(name="last_name",nullable = false,length = 150)
    private String last_name;
    @Column(name="email",nullable = false,length = 150)
    private String email;
    @Column(name="rutcompany",columnDefinition = "TEXT")
    private String rutcompany;
    @Column(name="company",nullable = false,length = 150)
    private String company;
    @Column(name="password",nullable = false,length = 150)
    private String password;
    @Column(name="idkey",length = 150)
    private String idkey;
    @Column(name="validate")
    private int validate;
    @Column(name="createdate")
    private Date createdate;
    @Column(name="validatedate")
    private Date validatedate;
    @Column(name="lastaccessdate")
    private Date lastaccessdate;
    @Column(name="typelicense")
    private int typelicense;
    @Column(name="dateexpiration")
    private Date dateexpiration;
    @Column(name="numberaccount")
    private int numberaccount;
    @Column(name = "active")
    private Boolean active;
    @Column(name="parametros",columnDefinition = "TEXT")
    private String paramettros;
    @OneToMany(mappedBy = "registeruser",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Usuario> usuarios=new HashSet<>();

    @OneToMany(mappedBy = "registeruser", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Perfil> perfiles = new HashSet<>();

    @OneToMany(mappedBy = "registeruser", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Permisos> permisos = new HashSet<>();

    @OneToMany(mappedBy = "registeruser",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Inventario> inventarios=new HashSet<>();

    /*  !!!
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="registeruser_roles",
    joinColumns = @JoinColumn(name = "registeruser_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles;

     */
}
