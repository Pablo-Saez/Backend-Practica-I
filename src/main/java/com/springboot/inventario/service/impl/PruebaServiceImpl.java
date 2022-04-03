package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Prueba;
import com.springboot.inventario.payload.PruebaDto;
import com.springboot.inventario.repository.PruebaRepository;
import com.springboot.inventario.service.PruebaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PruebaServiceImpl  implements PruebaService {
    public PruebaServiceImpl(PruebaRepository pruebaRepository) {
        this.pruebaRepository = pruebaRepository;
    }

    private PruebaRepository pruebaRepository;

    @Override
    public PruebaDto crearPrueba(PruebaDto pruebaDto) {
        Prueba prueba = mapToEntity(pruebaDto);
        pruebaRepository.save(prueba);
        return mapToDto(prueba);
    }

    @Override
    public PruebaDto obtenerPrueba(UUID pruebaUUID) {
        Prueba prueba = pruebaRepository.getById(pruebaUUID);
        return mapToDto(prueba);
    }

    @Override
    public List<PruebaDto> obtenerTodasLasPruebas() {
        List<Prueba> pruebas = pruebaRepository.findAll();
        return pruebas.stream().map(prueba -> mapToDto(prueba)).collect(Collectors.toList());
    }


    private PruebaDto mapToDto(Prueba prueba){
        PruebaDto pruebaDto = new PruebaDto();
        pruebaDto.setId(prueba.getId());
        pruebaDto.setLast_name(prueba.getLast_name());
        pruebaDto.setUsername(prueba.getUsername());
        pruebaDto.setValor(prueba.getValor());
        pruebaDto.setEmail(prueba.getEmail());
        return pruebaDto;
    }
    private Prueba mapToEntity(PruebaDto pruebaDto){
        Prueba prueba= new Prueba();
        prueba.setId(pruebaDto.getId());
        prueba.setUsername(pruebaDto.getUsername());
        prueba.setLast_name(pruebaDto.getLast_name());
        prueba.setValor(pruebaDto.getValor());
        prueba.setEmail(pruebaDto.getEmail());
        return prueba;


    }
}
