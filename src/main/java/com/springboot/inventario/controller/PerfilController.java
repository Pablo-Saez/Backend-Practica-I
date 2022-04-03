package com.springboot.inventario.controller;

import com.springboot.inventario.payload.PerfilDto;
import com.springboot.inventario.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("api")
public class PerfilController {
    private PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    //crear un perfil
    @Operation(summary = "Crear un perfil" )
    @ApiResponses(value = {
            @ApiResponse(responseCode="201",description = "Perfil guardado"),
            @ApiResponse(responseCode = "500",description = "Error al guardar perfil",content = @Content),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping("/web/perfil/{registeruserId}")
    public ResponseEntity<PerfilDto> createPerfil(
            @PathVariable(value="registeruserId") Long registeruserId,
            @RequestBody PerfilDto perfilDto
    ){

        return new ResponseEntity<>(perfilService.createPerfil(registeruserId,perfilDto), HttpStatus.CREATED);
    }
    //obtener todos los perfiles dado un registeruser id
    @Operation(summary = "Obtener todos los perfiles dado un registeruser id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Perfiles encontrados "),
            @ApiResponse(responseCode ="404",description = "Perfiles no encontrados",content = @Content)
    })
    @GetMapping("/web/perfiles/{registeruserId}")
    public List<PerfilDto> PerfilgetAll(
            @PathVariable(value = "registeruserId") Long registeruserId


    ){
        return perfilService.PerfilGetAll(registeruserId);

    }
    //obtener todos los perfiles sin id
    @Operation(summary = "Obtener todos los perfiles ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Perfiles encontrados "),
            @ApiResponse(responseCode ="404",description = "Perfiles no encontrados",content = @Content)
    })
    @GetMapping("/web/perfiles")
    public List<PerfilDto> perfilgetAllwithoutId(){
        return perfilService.PerfilGetAllWithoutId();
    }
    //obtener un perfil por su id
    @Operation(summary = "Obtener un peril por su id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Perfil encontrados "),
            @ApiResponse(responseCode ="404",description = "Perfil no encontrados",content = @Content)
    })
    @GetMapping("/web/perfil/{id}")
    public ResponseEntity<PerfilDto> getPerfilById(

            @PathVariable(value = "id") Long id
    ){
        PerfilDto perfilDto=perfilService.getPerfilById(id);
        return new ResponseEntity<>(perfilDto,HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/perfiles/registeruser/{registeruserId}")
    public List<PerfilDto> EncontrarPerfilesDeRegisteruser(@PathVariable(value = "registeruserId") Long registeruserId){
        return perfilService.EncontrarPerfilesDeRegisteruser(registeruserId);
    }
    //actualizar un perfil
    @Operation(summary = "Actualizar un prefil por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Perfil actualizado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Perfil no encontrado",content = @Content)

    })
    @PutMapping("web/perfil/{id}")
    public ResponseEntity<PerfilDto> updatePerfil(
            @PathVariable(value = "id") Long id,
            @RequestBody PerfilDto perfilDto
    ){
        PerfilDto updatedPerfil = perfilService.updatePerfil(id,perfilDto);
        return new ResponseEntity<>(updatedPerfil,HttpStatus.OK);
    }
    //eliminar un perfil
    @Operation(summary = "ELiminar un perfil por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Perfil eliminado"),
            @ApiResponse(responseCode = "404",description = "Perfil No encontrado",content = @Content)
    })
    @DeleteMapping("web/perfil/{id}")
    public ResponseEntity<String> deletePerfil(
            @PathVariable(value = "id") Long id
    ){
        perfilService.deletePerfil(id);
        return new ResponseEntity<>("Perfil eliminado",HttpStatus.OK);
    }


}
