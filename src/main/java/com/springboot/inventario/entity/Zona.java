package com.springboot.inventario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "zonas")
public class Zona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombreZona")
    private String nombreZona;
    @Column(name = "numeroZona")
    private int numeroZona;
    @Column(name = "ultimaRevision")
    private Date ultimaRevision;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_inventario", nullable = false)
    private Inventario inventario;

    @OneToMany(mappedBy = "zona")
    private Set<ZonaProducto> zonaProducto;

}
