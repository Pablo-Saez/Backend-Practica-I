package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Perfil;
import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.entity.Usuario;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.exception.apiException;
import com.springboot.inventario.payload.PerfilDto;
import com.springboot.inventario.repository.PerfilRepository;
import com.springboot.inventario.repository.RegisteruserRepository;
import com.springboot.inventario.repository.UsuarioRepository;
import com.springboot.inventario.service.PerfilService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilServiceImpl implements PerfilService {


    private PerfilRepository perfilRepository;
    private RegisteruserRepository registeruserRepository;
    private UsuarioRepository usuarioRepository;

    public PerfilServiceImpl(PerfilRepository perfilRepository, RegisteruserRepository registeruserRepository,UsuarioRepository usuarioRepository) {
        this.perfilRepository = perfilRepository;
        this.registeruserRepository = registeruserRepository;
        this.usuarioRepository=usuarioRepository;
    }



    //crear un perfil
    @Override
    public PerfilDto createPerfil(Long registeruserId,PerfilDto perfilDto) {
        Perfil perfil=mapToEntity(perfilDto);
        //retrieve registeruser by id
        Registeruser registeruser=registeruserRepository.findById(registeruserId).orElseThrow(
                ()-> new ResourceNotFoundException("Registeruser","id",registeruserId));
        perfil.setRegisteruser(registeruser);
        Perfil newPerfil = perfilRepository.save(perfil);
        PerfilDto perfilResponse = maptoDto(newPerfil);
        return perfilResponse;
    }

    @Override
    public List<PerfilDto> PerfilGetAll(Long registeruserId) {
        //Retrieve registeruser byid
        Registeruser registeruser=registeruserRepository.findById(registeruserId).orElseThrow(
                ()-> new ResourceNotFoundException("Registeruser","id",registeruserId));
        //retrieve usuario byid

        List<Perfil> perfiles = perfilRepository.findByRegisteruserId(registeruserId);
        return perfiles.stream().map(perfil -> maptoDto(perfil)).collect(Collectors.toList());
    }

    @Override
    public List<PerfilDto> PerfilGetAllWithoutId() {
        List<Perfil> perfiles = perfilRepository.findAll();
        return perfiles.stream().map(perfil -> maptoDto(perfil)).collect(Collectors.toList());
    }

    @Override
    public PerfilDto getPerfilById(Long perfilId) {
        Perfil perfil=perfilRepository.findById(perfilId).orElseThrow(()->
                new ResourceNotFoundException("Perfil","id",perfilId));
        return maptoDto(perfil);
    }

    @Override
    public PerfilDto updatePerfil(Long perfilId, PerfilDto perfilRequest) {
        Perfil perfil=perfilRepository.findById(perfilId).orElseThrow(()->
                new ResourceNotFoundException("Perfil","id",perfilId));

        if(perfilRequest.getNombre_perfil()!=null)
        perfil.setNombre_perfil(perfilRequest.getNombre_perfil());
        if(perfilRequest.getDesc_perfil()!=null)
        perfil.setDesc_perfil(perfilRequest.getDesc_perfil());
        if(perfilRequest.getEstado()!=null)
        perfil.setEstado(perfilRequest.getEstado());
        if(perfilRequest.getVisible()!=null)
        perfil.setVisible(perfilRequest.getVisible());
        if(perfilRequest.getCodigo()!=0
        )
        perfil.setCodigo(perfilRequest.getCodigo());
        Perfil updatedperfil = perfilRepository.save(perfil);
        return maptoDto(updatedperfil);
    }

    @Override
    public void deletePerfil(Long perfilId) {
        Perfil perfil=perfilRepository.findById(perfilId).orElseThrow(()-> new ResourceNotFoundException("Perfil","id",perfilId));
        perfilRepository.delete(perfil);
    }

    @Override
    public List<PerfilDto> EncontrarPerfilesDeRegisteruser(Long registeruserId) {
        List<Perfil> perfiles = perfilRepository.EncontrarPerfilesDeRegisteruser(registeruserId);
        return perfiles.stream().map(perfil -> maptoDto(perfil)).collect(Collectors.toList());
    }


    private PerfilDto maptoDto(Perfil perfil){
        PerfilDto perfilDto = new PerfilDto();
        perfilDto.setId(perfil.getId());
        perfilDto.setCodigo(perfil.getCodigo());
        perfilDto.setNombre_perfil(perfil.getNombre_perfil());
        perfilDto.setDesc_perfil(perfil.getDesc_perfil());
        perfilDto.setEstado(perfil.getEstado());
        perfilDto.setVisible(perfil.getVisible());
        perfilDto.setIdRegisteruser(perfil.getRegisteruser().getId());

        return perfilDto;
    }
    private Perfil mapToEntity(PerfilDto perfilDto){
        Perfil perfil=new Perfil();
        //perfil.setId(perfilDto.getId());
        perfil.setCodigo(perfilDto.getCodigo());
        perfil.setNombre_perfil(perfilDto.getNombre_perfil());
        perfil.setDesc_perfil(perfilDto.getDesc_perfil());
        perfil.setEstado(perfilDto.getEstado());
        perfil.setVisible(perfilDto.getVisible());
        return perfil;
    }
}
