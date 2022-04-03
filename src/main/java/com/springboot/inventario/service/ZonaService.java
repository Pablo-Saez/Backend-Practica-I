package com.springboot.inventario.service;

import com.springboot.inventario.payload.ZonaDto;

import java.util.List;

public interface ZonaService {
    ZonaDto crearZona(Long inventarioId,ZonaDto zonaDto);

    List<ZonaDto> obtenerZonasPorInventario(long inventarioId);

    ZonaDto actualizarZona(Long zonaId,ZonaDto zonaDto);

    ZonaDto obtenerZonaPorZonaId(Long zonaId);

    void deleteZona(Long zonaId);
}
