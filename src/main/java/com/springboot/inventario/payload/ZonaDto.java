package com.springboot.inventario.payload;

import com.springboot.inventario.entity.Inventario;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
@Data
public class ZonaDto {
    private Long id;
    private String nombreZona;
    private int numeroZona;
    private Date ultimaRevision;
    private Long id_inventario;

}
