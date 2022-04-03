package com.springboot.inventario.controller;


import com.springboot.inventario.payload.PruebaDto;
import com.springboot.inventario.service.PruebaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PruebaController {
    private PruebaService pruebaService;

    public PruebaController(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }


    @PostMapping("/prueba")
    public ResponseEntity<PruebaDto> crearPrueba(@Valid @RequestBody PruebaDto pruebaDto){
        return new ResponseEntity<>(pruebaService.crearPrueba(pruebaDto), HttpStatus.CREATED);
    }

    @GetMapping("/prueba/{UUID}")
    public ResponseEntity<PruebaDto> obtenerPruebaPorUUID(@PathVariable(value = "UUID")UUID id){
        return new ResponseEntity<>(pruebaService.obtenerPrueba(id),HttpStatus.OK);
    }

    @GetMapping("/pruebas")
    public List<PruebaDto> obtenerTodasLasPruebas(){
        return pruebaService.obtenerTodasLasPruebas();
    }
}
