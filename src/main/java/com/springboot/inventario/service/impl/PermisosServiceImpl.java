package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Perfil;
import com.springboot.inventario.entity.Permisos;
import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.payload.PermisosDto;
import com.springboot.inventario.repository.PerfilRepository;
import com.springboot.inventario.repository.PermisosRepository;
import com.springboot.inventario.repository.RegisteruserRepository;
import com.springboot.inventario.repository.UsuarioRepository;
import com.springboot.inventario.service.PermisosService;
import org.springframework.stereotype.Service;
import java.lang.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermisosServiceImpl implements PermisosService {
    private RegisteruserRepository registeruserRepository;
    private PerfilRepository perfilRepository;
    private PermisosRepository permisosRepository;
    public PermisosServiceImpl(PermisosRepository permisosRepository, RegisteruserRepository registeruserRepository, PerfilRepository perfilRepository) {

        this.permisosRepository = permisosRepository;
        this.registeruserRepository = registeruserRepository;
        this.perfilRepository=perfilRepository;
    }


    @Override
    public PermisosDto CreatePermiso(Long registeruserId,Long perfilId,PermisosDto permisosDto) {

        Permisos permisos= maptoEntity(permisosDto);
        Registeruser registeruser=registeruserRepository.findById(registeruserId).orElseThrow(
                ()-> new ResourceNotFoundException("Registeruser","id",registeruserId));
        Perfil perfil=perfilRepository.findById(perfilId).orElseThrow(
                ()-> new ResourceNotFoundException("Perfil","id",perfilId));
        permisos.setPerfil(perfil);
        permisos.setRegisteruser(registeruser);
        Permisos newPermiso=permisosRepository.save(permisos);
        return maptoDto(permisos);

    }



    @Override
    public PermisosDto GetPermiso(Long Idpermiso) {
        Permisos permiso=permisosRepository.findById(Idpermiso).orElseThrow(()->
                new ResourceNotFoundException("Permiso","id",Idpermiso));
        return maptoDto(permiso);
    }

    @Override
    public List<PermisosDto> PermisosGetAllWithoutId() {
        List<Permisos> permisos =permisosRepository.findAll();
        return permisos.stream().map(permisos1 -> maptoDto(permisos1)).collect(Collectors.toList());
    }

    @Override
    public List<PermisosDto> GetPermisoByPerfilId(Long perfilId) {
        List<Permisos> permisos = permisosRepository.findByPerfilId(perfilId);
        return permisos.stream().map(permisos1 -> maptoDto(permisos1)).collect(Collectors.toList());



    }

    @Override
    public PermisosDto updatePermiso(Long permisoId, PermisosDto permisoRequest) {
        Permisos permiso=permisosRepository.findById(permisoId).orElseThrow(()->
                new ResourceNotFoundException("Permiso","id",permisoId));
        if(permisoRequest.getVisible()!=null)
        permiso.setVisible(permisoRequest.getVisible());
        if(permisoRequest.getCrud()!=null)
        permiso.setCrud(permisoRequest.getCrud());
        Permisos updatedPermiso = permisosRepository.save(permiso);
        return maptoDto(updatedPermiso);
    }

    @Override
    public void deletePermiso(Long permisoId) {
        Permisos permiso=permisosRepository.findById(permisoId).orElseThrow(()->
                new ResourceNotFoundException("Permiso","id",permisoId));
        permisosRepository.delete(permiso);
    }

    @Override
    public List<PermisosDto> ObtenerTodosLosPermisosDeUnRegisteruser(Long registeruserId) {
        List<Permisos> permisos=permisosRepository.ObtenerTodosLosPermisosDeUnRegisteruser(registeruserId);
        return permisos.stream().map(permisos1 -> maptoDto(permisos1)).collect(Collectors.toList());

    }

    @Override
    public List<PermisosDto> obenterTodosLospermisosDeUnPerfilyRegisteruser(Long perfilId, Long registeruserId) {
        List<Permisos> permisos=permisosRepository.obenterTodosLospermisosDeUnPerfilyRegisteruser(perfilId,registeruserId);
        return permisos.stream().map(permisos1 -> maptoDto(permisos1)).collect(Collectors.toList());
    }


    private PermisosDto maptoDto(Permisos permisos){
        PermisosDto permisosDto = new PermisosDto();
        permisosDto.setId(permisos.getId());
        permisosDto.setCrud(permisos.getCrud());
        permisosDto.setVisible(permisos.getVisible());
        permisosDto.setId_registeruser(permisos.getRegisteruser().getId());
        permisosDto.setId_perfil(permisos.getPerfil().getId());
        permisosDto.setId_menu(permisos.getMenu().getId());
        permisosDto.setId_menuChildren(permisos.getMenuChildren().getId());
       // permisosDto.setId_menu(permisos.getMenu().getId());
        //permisosDto.setId_menuChildren(permisos.getMenuChildren().getId());
        return permisosDto;
    }
    private Permisos maptoEntity(PermisosDto permisosDto){
        Permisos permisos=new Permisos();
        permisos.setId(permisosDto.getId());
        permisos.setCrud(permisosDto.getCrud());
        permisos.setVisible(permisosDto.getVisible());

        return permisos;
    }
}
