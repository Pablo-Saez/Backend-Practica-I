package com.springboot.inventario.service;

import com.springboot.inventario.payload.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    UsuarioDto createUsuario(Long registeruserId,UsuarioDto usuarioDto);

    List<UsuarioDto> getAllUsuariosWithoutID();

    List<UsuarioDto> getUsuariosByRegisteruserId(long registeruserId);

    UsuarioDto getUsuarioById(Long registeruserId,Long usuarioId);

    UsuarioDto upadteUsuario(Long registeruserId,Long usuarioId,UsuarioDto usuarioRequest);

    void deleteUsuario(Long registeruserId,Long usuarioId);

    void deleteUsuario2(Long usuarioId);
}
