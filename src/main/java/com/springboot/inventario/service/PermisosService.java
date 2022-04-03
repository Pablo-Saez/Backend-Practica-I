package com.springboot.inventario.service;

import com.springboot.inventario.entity.Permisos;
import com.springboot.inventario.payload.PerfilDto;
import com.springboot.inventario.payload.PermisosDto;

import java.util.List;

public interface PermisosService {
    PermisosDto CreatePermiso(Long registeruserId,Long perfilId,PermisosDto permisosDto);

    PermisosDto GetPermiso(Long Idpermiso);

    List<PermisosDto> PermisosGetAllWithoutId();

    List<PermisosDto> GetPermisoByPerfilId(Long perfilId);

    PermisosDto updatePermiso(Long permisoId,PermisosDto permisoRequest);

    void deletePermiso(Long permisoId);

    List<PermisosDto> ObtenerTodosLosPermisosDeUnRegisteruser(Long registeruserId);

    List<PermisosDto> obenterTodosLospermisosDeUnPerfilyRegisteruser(Long perfilId,Long registeruserId);
}
