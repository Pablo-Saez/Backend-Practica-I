package com.springboot.inventario.controller;

import com.springboot.inventario.entity.ZonaProducto;
import com.springboot.inventario.payload.ZonaProductoDto;
import com.springboot.inventario.service.ZonaProductoService;
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
public class ZonaProductoController {
    private ZonaProductoService zonaProductoService;

    public ZonaProductoController(ZonaProductoService zonaProductoService) {
        this.zonaProductoService = zonaProductoService;
    }
    @Operation(summary = "Enlazar un producto con una zona (crea una relacion de tabla zona con tabla producto) ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Relacion creada"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Producto o zona no encontrados",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping("/agregar")
    public ResponseEntity<ZonaProductoDto> agregarZonaProducto(@Valid @RequestBody ZonaProductoDto zonaProductoDto){
        return new ResponseEntity<>(zonaProductoService.AgregarProducto(zonaProductoDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Encuentra una relacion dado el id de producto e id de zona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Relacion encontrada"),

            @ApiResponse(responseCode ="404",description = "Relacion no encontrada",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/zona/{id_zona}/producto/{id_producto}")
    public ResponseEntity<ZonaProductoDto> obtenerZonaProducto(
        @PathVariable(value = "id_zona")Long id_zona,
        @PathVariable(value = "id_producto") Long id_producto
    ){
        ZonaProductoDto zonaProductoDto=zonaProductoService.ObtenerZonaProducto(id_zona,id_producto);
        return new ResponseEntity<>(zonaProductoDto,HttpStatus.OK);
    }
    @Operation(summary = "Entrega todas las relaciones con productos de una zona dado su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Relaciones encontradas "),

            @ApiResponse(responseCode ="404",description = "Relaciones no encontradas",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/productos/zona/{idZona}")
    public List<ZonaProductoDto> ObtenerProductosDeUnaZona(
            @PathVariable(value = "idZona")Long idZona
    ){
        return zonaProductoService.obtenerProductosDeUnaZona(idZona);
    }

    @Operation(summary = "Encuentra una relacion dado el id de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Relacion encontrada "),

            @ApiResponse(responseCode ="404",description = "Relacion no encontrada",content = @Content)
    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/zonaproducto/{productoId}")
    public ResponseEntity<ZonaProductoDto> obtenerZonaProductoPorIdProducto(
            @PathVariable(value = "productoId") Long productoId
    ){
        return new ResponseEntity<>(zonaProductoService.obtenerZonaProductoPorIdProducto(productoId),HttpStatus.OK);
    }
    @Operation(summary = "Actualiza una Relacion (Los id's tienen que ir en el body)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Relacion encontrada "),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Relacion no encontrada",content = @Content)
    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PutMapping("/actualizar")
    public ResponseEntity<ZonaProductoDto> actualizarZonaProducto(
            @Valid @RequestBody ZonaProductoDto zonaProductoDto
    ){
        ZonaProductoDto zonaProductoDto1=zonaProductoService.ActualizarStock(zonaProductoDto);
        return new ResponseEntity<>(zonaProductoDto1,HttpStatus.OK);
    }
    @Operation(summary = "Elimina una relacion de zona con producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Relacion eliminada"),

            @ApiResponse(responseCode ="404",description = "Relacion no encontrada",content = @Content)
    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @DeleteMapping("/eliminar/{id_zona}/producto/{id_producto}")
    public ResponseEntity<String> EliminarProductoDeZona(
            @PathVariable(value = "id_zona")Long id_zona,
            @PathVariable(value = "id_producto") Long id_producto
    ){
        zonaProductoService.EliminarProductoDeZona(id_zona,id_producto);
        return new ResponseEntity<>("Producto retirado de la zona",HttpStatus.OK);
    }

}
