package com.springboot.inventario.controller;

import com.springboot.inventario.payload.ProductoDto;
import com.springboot.inventario.service.ProductoService;
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
public class ProductoController {
    private ProductoService productoService;


    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @Operation(summary = "Crea un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Producto creado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content)


    })


    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping("/producto")
    public ResponseEntity<ProductoDto> crearProducto(
            @Valid @RequestBody ProductoDto productoDto
    ){
        return new ResponseEntity<>(productoService.crearProducto(productoDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Entrega un producto con id idProducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto Encontrado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Producto no encontrado",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<ProductoDto> obtenerProductoPorID(
            @PathVariable(value = "idProducto") Long idProducto
    ){
        return ResponseEntity.ok(productoService.obtenerProducto(idProducto));
    }

    @Operation(summary = "Entrega todos los productos asociados a un inventario ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Productos encontrados"),
            @ApiResponse(responseCode ="404",description = "Productos o inventario no encontrado",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("productos/{inventarioId}")
    public List<ProductoDto> EncontrarTodosLosProductosDeUnInventario(@PathVariable(value = "inventarioId") Long inventarioId){
        return productoService.EncontrarTodosLosProductosDeUnInventario(inventarioId);
    }

    @Operation(summary = "Actualiza un producto con id idProducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto actualizado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Producto no encontrado",content = @Content)

    })
    @PutMapping("/producto/{idProducto}")
    public ResponseEntity<ProductoDto> actualizarProducto(
            @PathVariable(value = "idProducto") Long idProducto,
            @RequestBody ProductoDto productoDto
            ){
        ProductoDto response = productoService.ActualizarProducto(idProducto,productoDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto con id idProducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto eliminado"),

            @ApiResponse(responseCode ="404",description = "Producto no encontrado",content = @Content)

    })
    @DeleteMapping("/producto/{idProducto}")
    public ResponseEntity<String> eliminarProducto(
            @PathVariable(value = "idProducto") Long idProducto
    ){
        productoService.eliminarProducto(idProducto);
        return new ResponseEntity<>("Producto eliminado",HttpStatus.OK);
    }
}
