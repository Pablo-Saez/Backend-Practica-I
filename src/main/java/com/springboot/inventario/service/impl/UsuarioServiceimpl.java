package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Perfil;
import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.entity.Usuario;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.exception.apiException;
import com.springboot.inventario.payload.UsuarioDto;
import com.springboot.inventario.repository.PerfilRepository;
import com.springboot.inventario.repository.RegisteruserRepository;
import com.springboot.inventario.repository.UsuarioRepository;
import com.springboot.inventario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceimpl implements UsuarioService {

    private PerfilRepository perfilRepository;
    private UsuarioRepository usuarioRepository;
    private RegisteruserRepository registeruserRepository;

    public UsuarioServiceimpl(UsuarioRepository usuarioRepository,RegisteruserRepository registeruserRepository,PerfilRepository perfilRepository) {
        this.usuarioRepository = usuarioRepository;
        this.registeruserRepository=registeruserRepository;
        this.perfilRepository=perfilRepository;
    }


    @Override
    public UsuarioDto createUsuario(Long registeruserId,UsuarioDto usuarioDto){
        Usuario usuario=mapToEntity(usuarioDto);
        //retrieve registeruser entity by id
        Registeruser registeruser=registeruserRepository.findById(registeruserId).orElseThrow(
                ()-> new ResourceNotFoundException("Registeruser","id",registeruserId));

        usuario.setRegisteruser(registeruser);
        Long idPerfil;

        if(usuarioDto.getId_perfil()!=null) {
            idPerfil = usuarioDto.getId_perfil();
            Perfil perfil = perfilRepository.findById(idPerfil).orElseThrow(()-> new ResourceNotFoundException("Perfil","id",idPerfil));
            usuario.setPerfil(perfil);
            Usuario newUsuario = usuarioRepository.save(usuario);
            return mapToDto(newUsuario);
        }
        else{
            /*Perfil perfil = new Perfil();
            usuario.setPerfil(perfil);*/
            Usuario newUsuario = usuarioRepository.save(usuario);
            return mapToDto(newUsuario);
        }


    }

    @Override
    public List<UsuarioDto> getAllUsuariosWithoutID() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> mapToDto(usuario)).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDto> getUsuariosByRegisteruserId(long registeruserId) {
        //retrieve usuarios by postID
        List<Usuario> usuarios = usuarioRepository.findByRegisteruserId(registeruserId);
    //convert list of usuarios entities to list of usuarios dto's
        return usuarios.stream().map(usuario -> mapToDto(usuario)).collect(Collectors.toList());
    }

    @Override
    public UsuarioDto getUsuarioById(Long registeruserId, Long usuarioId) {
        //Retrieve registeruser byid
        Registeruser registeruser=registeruserRepository.findById(registeruserId).orElseThrow(
                ()-> new ResourceNotFoundException("Registeruser","id",registeruserId));
        //retrieve usuario byid
        Usuario usuario= usuarioRepository.findById(usuarioId).orElseThrow(()->
                new ResourceNotFoundException("Usuario","id",usuarioId));

        if(!usuario.getRegisteruser().getId().equals(registeruser.getId())){
            throw new apiException(HttpStatus.BAD_REQUEST,"Usuario no pertenece a registeruser");
        }
        return mapToDto(usuario);

    }

    @Override
    public UsuarioDto upadteUsuario(Long registeruserId, Long usuarioId, UsuarioDto usuarioRequest) {
        //Retrieve registeruser byid
        Registeruser registeruser=registeruserRepository.findById(registeruserId).orElseThrow(
                ()-> new ResourceNotFoundException("Registeruser","id",registeruserId));
        //retrieve usuario byid
        Usuario usuario= usuarioRepository.findById(usuarioId).orElseThrow(()->
                new ResourceNotFoundException("Usuario","id",usuarioId));

        if(!usuario.getRegisteruser().getId().equals(registeruser.getId())){
            throw new apiException(HttpStatus.BAD_REQUEST,"Usuario no pertenece a registeruser");
        }

        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setApellido(usuarioRequest.getApellido());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setPassword(usuarioRequest.getPassword());
        usuario.setRut(usuarioRequest.getRut());
        usuario.setIdkey(usuarioRequest.getIdkey());
        usuario.setValidatekey(usuarioRequest.getValidatekey());
        usuario.setLastaccessdate(usuarioRequest.getLastaccessdate());
        usuario.setValidate(usuarioRequest.getValidate());
        //usuario.setActive(usuarioRequest.getActive()); revisar error !!!
        //usuario.set(usuarioRequest.getIdperfil());
        usuario.setParametros(usuarioRequest.getParametros());


        Usuario updatedUsuario =usuarioRepository.save(usuario);
        return mapToDto(updatedUsuario);
    }

    @Override
    public void deleteUsuario(Long registeruserId, Long usuarioId) {
        //Retrieve registeruser byid
        Registeruser registeruser=registeruserRepository.findById(registeruserId).orElseThrow(
                ()-> new ResourceNotFoundException("Registeruser","id",registeruserId));
        //retrieve usuario byid
        Usuario usuario= usuarioRepository.findById(usuarioId).orElseThrow(()->
                new ResourceNotFoundException("Usuario","id",usuarioId));

        if(!usuario.getRegisteruser().getId().equals(registeruser.getId())){
            throw new apiException(HttpStatus.BAD_REQUEST,"Usuario no pertenece a registeruser");
        }
        usuarioRepository.delete(usuario);

    }

    @Override
    public void deleteUsuario2(Long usuarioId) {
        Usuario usuario= usuarioRepository.findById(usuarioId).orElseThrow(()->
                new ResourceNotFoundException("Usuario","id",usuarioId));
        usuarioRepository.delete(usuario);
    }


    private UsuarioDto mapToDto(Usuario usuario){
        UsuarioDto usuarioDto= new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setId_registeruser(usuario.getRegisteruser().getId());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setApellido(usuario.getApellido());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setPassword(usuario.getPassword());
        usuarioDto.setRut(usuario.getRut());
        usuarioDto.setIdkey(usuario.getIdkey());
        usuarioDto.setValidatekey(usuario.getValidatekey());
        usuarioDto.setLastaccessdate(usuario.getLastaccessdate());
        usuarioDto.setValidate(usuario.getValidate());
        usuarioDto.setActive(usuario.getActive());
        usuarioDto.setParametros(usuario.getParametros());
       // usuarioDto.setId_perfil(usuario.getPerfil().getId());
        usuarioDto.setId_perfil(usuario.getPerfil().getId()); //
        return usuarioDto;
    }

    private Usuario mapToEntity(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        //usuario.setId(usuarioDto.getId());
        //usuario.setId_registeruser(usuarioDto.getId_registeruser());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setRut(usuarioDto.getRut());
        usuario.setIdkey(usuarioDto.getIdkey());
        usuario.setValidatekey(usuarioDto.getValidatekey());
        usuario.setLastaccessdate(usuarioDto.getLastaccessdate());
        usuario.setValidate(usuarioDto.getValidate());
        usuario.setActive(usuarioDto.getActive());

        usuario.setParametros(usuarioDto.getParametros());


        return usuario;


    }
}
