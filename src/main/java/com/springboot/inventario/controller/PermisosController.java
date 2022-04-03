package com.springboot.inventario.controller;

import com.springboot.inventario.payload.PermisosDto;
import com.springboot.inventario.service.PermisosService;
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
@RequestMapping("/api")
public class PermisosController {

    private PermisosService permisosService;
    public PermisosController(PermisosService permisosService) {
        this.permisosService = permisosService;
    }

    //crear un permiso dado un regsiteruser Id y un perfil
    @Operation(summary = "Crear un permiso dado un regsiteruser Id y un perfil" )
    @ApiResponses(value = {
            @ApiResponse(responseCode="201",description = "Permiso guardado"),
            @ApiResponse(responseCode = "500",description = "Error al guardar permiso",content = @Content),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)

    })
    @PostMapping("/{registeruserId}/perfil/{perfilId}/perfiles_permisos")
    public ResponseEntity<PermisosDto> createPermiso(
            @PathVariable(value = "registeruserId") Long registeruserId,
            @PathVariable(value="perfilId")  Long perfilId,
            @RequestBody PermisosDto permisosDto){
        return new ResponseEntity<>(permisosService.CreatePermiso(registeruserId,perfilId,permisosDto), HttpStatus.CREATED);
    }
    //obtener un permiso por su id
    @Operation(summary = "Obtener un permiso por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permiso encontrado "),
            @ApiResponse(responseCode ="404",description = "Permiso no encontrado",content = @Content)
    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/perfiles_permisos/{id}")
    public ResponseEntity<PermisosDto> getPermisoById(
            @PathVariable(value = "id") Long id
    ){
        PermisosDto permisosDto=permisosService.GetPermiso(id);
        return new ResponseEntity<>(permisosDto,HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/perfiles_permisos/registeruser/{registeruserId}")
    public List<PermisosDto> ObtenerTodosLosPermisosDeUnRegisteruser(@PathVariable(value = "registeruserId") Long registeruserId){
        return permisosService.ObtenerTodosLosPermisosDeUnRegisteruser(registeruserId);
    }
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/perfiles_permisos/perfil/{perfilId}/registeruser/{registeruserId}")
    public List<PermisosDto> obenterTodosLospermisosDeUnPerfilyRegisteruser(
            @PathVariable(value = "perfilId") Long perfilId,
            @PathVariable(value = "registeruserId") Long registeruserId
    ){
        return permisosService.obenterTodosLospermisosDeUnPerfilyRegisteruser(perfilId,registeruserId);

    }
    //obtener los permisos de un perfil
    @Operation(summary = "Obtener los permisos de un perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permisos encontrados "),
            @ApiResponse(responseCode ="404",description = "Permisos no encontrados",content = @Content)
    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/perfil/{perfilId}/perfil_permisos")
    public List<PermisosDto> permisosByPerfilId(@PathVariable(value = "perfilId")Long perfilId){
        return permisosService.GetPermisoByPerfilId(perfilId);
    }
    //obtener todos los permisos
    @Operation(summary = "Obtener todos los permisos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permisos encontrados "),
            @ApiResponse(responseCode ="404",description = "Permisos no encontrados",content = @Content)
    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("perfiles_permisos")
    public List<PermisosDto> permisosGetAllWithoutId(){
        return permisosService.PermisosGetAllWithoutId();
    }
    //actualizar un permiso por su id
    @Operation(summary = "Actualizar un permiso por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permiso actualizado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Permiso no encontrado",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    @PutMapping("/perfiles_permisos/{id}")
    public ResponseEntity<PermisosDto> updatePermiso(
            @PathVariable(value ="id" ) Long id,
            @RequestBody PermisosDto permisosDto
    ){
        PermisosDto updatedPermiso=permisosService.updatePermiso(id,permisosDto);
        return new ResponseEntity<>(updatedPermiso,HttpStatus.OK);

    }
    //eliminar un permiso por su id
    @Operation(summary = "Eliminar un permiso por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permiso eliminado"),
            @ApiResponse(responseCode = "404",description = "Permiso No encontrado",content = @Content)
    })
    @DeleteMapping("perfiles_permisos/{id}")
    public ResponseEntity<String> deletePermiso(
            @PathVariable(value = "id") Long id
    ){
        permisosService.deletePermiso(id);
        return new ResponseEntity<>("Permiso eliminado",HttpStatus.OK);
    }


}
