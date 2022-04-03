package com.springboot.inventario.service;

import com.springboot.inventario.entity.Perfil;
import com.springboot.inventario.payload.PerfilDto;

import java.util.List;

public interface PerfilService {
    PerfilDto createPerfil(Long registeruserId,PerfilDto perfilDto);

    List<PerfilDto> PerfilGetAll(Long registeruserId);

    List<PerfilDto> PerfilGetAllWithoutId();

    PerfilDto getPerfilById(Long perfilId);

    PerfilDto updatePerfil(Long perfilId,PerfilDto PerfilRequest);

    void deletePerfil(Long perfilId);

    List<PerfilDto> EncontrarPerfilesDeRegisteruser(Long registeruserId);


}
