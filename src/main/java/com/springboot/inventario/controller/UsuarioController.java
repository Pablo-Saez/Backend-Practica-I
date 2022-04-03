package com.springboot.inventario.controller;

import com.springboot.inventario.entity.Usuario;
import com.springboot.inventario.payload.UsuarioDto;
import com.springboot.inventario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    //crear usuario
    @Operation(summary = "Crear un usuario" )
    @ApiResponses(value = {
            @ApiResponse(responseCode="201",description = "Usuario guardado"),
            @ApiResponse(responseCode = "500",description = "Error al guardar usuario",content = @Content),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)

    })
    @PostMapping("/web/{registeruserId}/usuario")
    public ResponseEntity<UsuarioDto> createUser(
            @PathVariable(value = "registeruserId") Long registeruserId,
            @Valid @RequestBody UsuarioDto usuarioDto
    ){

        return new ResponseEntity<>(usuarioService.createUsuario(registeruserId,usuarioDto), HttpStatus.CREATED);

    }
    //obtener todos los usuarios
    @Operation(summary = "Obtener todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuarios encontrados "),
            @ApiResponse(responseCode ="404",description = "Usuarios no encontrados",content = @Content)
    })
    @GetMapping("/web/usuario")
    public List<UsuarioDto> getUsuarioswithoutId(){

        return usuarioService.getAllUsuariosWithoutID();
    }

    @Operation(summary = "Obtener todos los usuarios que pertenecen a un registeruser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuarios encontrados "),
            @ApiResponse(responseCode ="404",description = "Usuarios no encontrados",content = @Content)
    })
    @GetMapping("/web/{registeruserId}/usuario")
    public List<UsuarioDto> getUsuariosByRegisteruserId(@PathVariable(value = "registeruserId") Long registeruserId){
        return usuarioService.getUsuariosByRegisteruserId(registeruserId);
    }
    @Operation(summary = "Obtener un usuario en especifico dado su id y un registeruserId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario encontrado "),
            @ApiResponse(responseCode ="404",description = "usuario no encontrado",content = @Content)
    })
    @GetMapping("web/{registeruserId}/usuario/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(
            @PathVariable(value = "registeruserId") Long registeruserId,
            @PathVariable(value = "id") Long usuarioId
    ){
        UsuarioDto usuarioDto=usuarioService.getUsuarioById(registeruserId,usuarioId);
        return new ResponseEntity<>(usuarioDto,HttpStatus.OK);
    }
    @Operation(summary = "Actualizar un usuario por su id dado un registeruserId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario actualizado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Usuario no encontrado",content = @Content)

    })
    @PutMapping("/web/{registeruserId}/usuario/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario (
            @PathVariable(value = "registeruserId") Long registeruserId,
            @PathVariable(value = "id") Long usuarioId,
            @Valid @RequestBody UsuarioDto usuarioDto
    ){
        UsuarioDto updatedUsuario = usuarioService.upadteUsuario(registeruserId,usuarioId,usuarioDto);
        return new ResponseEntity<>(updatedUsuario,HttpStatus.OK);
    }
    @Operation(summary = "ELiminar un usuario por su id y registeruserId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404",description = "Usuario No encontrado",content = @Content)
    })
    @DeleteMapping("/web/{registeruserId}/usuario/{id}")
    public ResponseEntity<String> deleteUsuario(
            @PathVariable(value = "registeruserId") Long registeruserId,
            @PathVariable(value="id") Long usuarioId
    ){
        usuarioService.deleteUsuario(registeruserId,usuarioId);
        return new ResponseEntity<>("Usuario eliminado",HttpStatus.OK);

    }
    @Operation(summary = "Eliminar un usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404",description = "Usuario No encontrado",content = @Content)
    })
    @DeleteMapping("/web/usuario/{id}")
    public ResponseEntity<String> deleteUsuario2(@PathVariable(value = "id") Long id){
        usuarioService.deleteUsuario2(id);
        return new ResponseEntity<>("Usuario eliminado",HttpStatus.OK);
    }



}
