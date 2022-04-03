package com.springboot.inventario.service;

import com.springboot.inventario.payload.PruebaDto;

import java.util.List;
import java.util.UUID;

public interface PruebaService {

    PruebaDto crearPrueba(PruebaDto pruebaDto);

    PruebaDto obtenerPrueba(UUID pruebaUUID);

    List<PruebaDto> obtenerTodasLasPruebas();
}
