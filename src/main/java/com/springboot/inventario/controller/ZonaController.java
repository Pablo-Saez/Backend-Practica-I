package com.springboot.inventario.controller;

import com.springboot.inventario.payload.ZonaDto;
import com.springboot.inventario.service.ZonaService;
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
public class ZonaController {

    private ZonaService zonaService;
    public ZonaController(ZonaService zonaService) {
        this.zonaService = zonaService;
    }


    @Operation(summary = "Crea una zona en el inventario con id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Inventarios actualizado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Inventario no encontrados",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping("/zona/{inventarioId}")
    public ResponseEntity<ZonaDto> crearZona(
            @PathVariable(value = "inventarioId") Long inventarioId,
            @Valid @RequestBody ZonaDto zonaDto
    ){
        return new ResponseEntity<>(zonaService.crearZona(inventarioId,zonaDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Entrega todas las zonas de un inventario ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Zonas encontradas"),

            @ApiResponse(responseCode ="404",description = "Zonas o inventario no encontrados",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/zona/inventario/{inventarioId}")
    public List<ZonaDto> ZonasInventario(
            @PathVariable(value = "inventarioId") Long inventarioId
    ){
        return zonaService.obtenerZonasPorInventario(inventarioId);
    }
    @Operation(summary = "Entrega una zona de acuerdo a su Id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Zona encontrada"),
            @ApiResponse(responseCode ="404",description = "zona no encontrada",content = @Content)
    })
    @GetMapping("/zona/{zonaId}")
    public ResponseEntity<ZonaDto> obtenerZonaPorZonaId (@PathVariable(value ="zonaId" ) Long zonaId){
        return new ResponseEntity<>(zonaService.obtenerZonaPorZonaId(zonaId),HttpStatus.OK);
    }
    @Operation(summary = "Actualizar una zona ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Zona actualizada"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Zona no encontrada",content = @Content)

    })
    //PASAR ID DE INVENTARIO COMO PARAMETRO EN EL BODYDTO, CAMBIABLE
    @PutMapping("/zona/{zonaId}")
    public ResponseEntity<ZonaDto> actualizarZona(
            @PathVariable(value = "zonaId") Long zonaId,
            @Valid @RequestBody ZonaDto zonaDto
    ){
        ZonaDto updatedZona= zonaService.actualizarZona(zonaId,zonaDto);
        return new ResponseEntity<>(updatedZona,HttpStatus.OK);
    }

    @Operation(summary = "Eliminar la zona con id zonaId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Zona eliminada"),

            @ApiResponse(responseCode ="404",description = "Zona no encontrada",content = @Content)

    })
    @DeleteMapping("/zona/{zonaId}")
    public ResponseEntity<String> eliminarZona(
            @PathVariable(value = "zonaId") Long zonaId
    ){
        zonaService.deleteZona(zonaId);
        return new ResponseEntity<>("Zona eliminada",HttpStatus.OK);
    }
}
