package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Inventario;
import com.springboot.inventario.entity.Zona;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.payload.ZonaDto;
import com.springboot.inventario.repository.InventarioRepository;
import com.springboot.inventario.repository.ZonaRepository;
import com.springboot.inventario.service.ZonaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZonaServiceImpls implements ZonaService {
    private ZonaRepository zonaRepository;
    private InventarioRepository inventarioRepository;

    public ZonaServiceImpls(ZonaRepository zonaRepository, InventarioRepository inventarioRepository) {
        this.zonaRepository = zonaRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public ZonaDto crearZona(Long inventarioId, ZonaDto zonaDto) {
        Zona zona=mapToEntity(zonaDto);
        Inventario inventario= inventarioRepository.findById(inventarioId).orElseThrow(()
        -> new ResourceNotFoundException("Inventario","id",inventarioId));
        zona.setInventario(inventario);
        Zona newZona = zonaRepository.save(zona);
        return mapToDto(newZona);
    }

    @Override
    public List<ZonaDto> obtenerZonasPorInventario(long inventarioId) {
        List<Zona> zonas = zonaRepository.findByInventarioId(inventarioId);
        return zonas.stream().map(zona -> mapToDto(zona)).collect(Collectors.toList());
    }

    @Override
    public ZonaDto actualizarZona(Long zonaId,ZonaDto zonaDto) {
        Zona zona = zonaRepository.findById(zonaId).orElseThrow(
                ()->new ResourceNotFoundException("Zona","id",zonaId));
        if(zonaDto.getNombreZona()!=null)
        zona.setNombreZona(zonaDto.getNombreZona());
        if(zonaDto.getNumeroZona() != 0)
        zona.setNumeroZona(zonaDto.getNumeroZona());
        if(zonaDto.getUltimaRevision()!=null)
        zona.setUltimaRevision(zonaDto.getUltimaRevision());
        Zona zonaActualizada= zonaRepository.save(zona);
        return mapToDto(zonaActualizada);
    }

    @Override
    public ZonaDto obtenerZonaPorZonaId(Long zonaId) {
        Zona zona = zonaRepository.findById(zonaId).orElseThrow(
                ()->new ResourceNotFoundException("Zona","id",zonaId));
        return mapToDto(zona);
    }

    @Override
    public void deleteZona(Long zonaId) {
        Zona zona = zonaRepository.findById(zonaId).orElseThrow(
                ()->new ResourceNotFoundException("Zona","id",zonaId));
        zonaRepository.delete(zona);
    }

    private ZonaDto mapToDto(Zona zona){
        ZonaDto zonaDto = new ZonaDto();
        zonaDto.setId(zona.getId());
        zonaDto.setNombreZona(zona.getNombreZona());
        zonaDto.setNumeroZona(zona.getNumeroZona());
        zonaDto.setId_inventario(zona.getInventario().getId());
        return zonaDto;
    }
    private Zona mapToEntity(ZonaDto zonaDto){
        Zona zona = new Zona();
        zona.setNombreZona(zonaDto.getNombreZona());
        zona.setNumeroZona(zonaDto.getNumeroZona());
        return zona;
    }
}
