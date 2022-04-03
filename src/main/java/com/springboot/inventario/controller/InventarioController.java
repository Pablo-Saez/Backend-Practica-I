package com.springboot.inventario.controller;

import com.springboot.inventario.payload.InventarioDto;
import com.springboot.inventario.payload.ProductoDto;
import com.springboot.inventario.service.InventarioService;
import com.springboot.inventario.service.RegisteruserService;
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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api")
public class InventarioController {
    private InventarioService inventarioService;
    private RegisteruserService registeruserService;

    public InventarioController(InventarioService inventarioService, RegisteruserService registeruserService) {
        this.inventarioService = inventarioService;
        this.registeruserService = registeruserService;
    }
    @Operation(summary = "Crear un inventario dado un registeruser Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Inventario creado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Registeruser no encontrado",content = @Content)

    })

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping("/inventario/{id}")
    public ResponseEntity<InventarioDto> createInventario(
            @PathVariable(value="id") Long id,
            @Valid @RequestBody InventarioDto inventarioDto
    ){
        InventarioDto response = inventarioService.createInventario(id,inventarioDto);
        if(response==null){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(inventarioService.createInventario(id, inventarioDto), HttpStatus.CREATED);
        }
    }
    @Operation(summary = "Obtiene todos los inventarios de un registeruser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Inventarios Encontrados"),
            @ApiResponse(responseCode ="404",description = "Inventarios no encontrados",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/inventarios/{id}")
    public List<InventarioDto> obtenerInventariosDeRegisteruser(
            @PathVariable(value = "id") Long id){
        return inventarioService.obtenerInventariosConRegisteruserId(id);
    }

    @Operation(summary = "Obtiene todos los inventarios ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Inventarios Encontrados"),
            @ApiResponse(responseCode ="404",description = "Inventarios no encontrados",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/inventarios")
    public List<InventarioDto> obtenerInventariosDeRegisteruser(){
        return inventarioService.obtenerTodosLosInventariosSinId();
    }


    @Operation(summary = "Actualiza un inventario dado su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Inventarios actualizado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Inventario no encontrados",content = @Content)

    })
    @PutMapping("/inventario/{id}")
    public ResponseEntity<InventarioDto> actualizarInventarioPorId(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody InventarioDto inventarioDto
    ){
        InventarioDto inventarioActualizado= inventarioService.actualizarInventario(id,inventarioDto);
        return new ResponseEntity<>(inventarioActualizado,HttpStatus.OK);
    }


    @Operation(summary = "Elimina un inventario dado su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Inventarios eliminado"),

            @ApiResponse(responseCode ="404",description = "Inventario no encontrado",content = @Content)

    })
    @DeleteMapping("/inventario/{id}")
    public ResponseEntity<String> eliminarInventario(
            @PathVariable(value = "id") Long id

    ){
        inventarioService.deleteInventario(id);
        return  new ResponseEntity<>("Inventario eliminado",HttpStatus.OK);
    }

}
