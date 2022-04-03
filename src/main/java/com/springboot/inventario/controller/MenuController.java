package com.springboot.inventario.controller;

import com.springboot.inventario.payload.MenuDto;
import com.springboot.inventario.service.MenuService;
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
@RequestMapping("api/web")
public class MenuController {
    private MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    //Crear un menu
    @Operation(summary = "Crear un menu" )
    @ApiResponses(value = {
            @ApiResponse(responseCode="201",description = "Menu guardado"),
            @ApiResponse(responseCode = "500",description = "Error al guardar menu",content = @Content),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping("/menu")
    public ResponseEntity<MenuDto> createMenu(
            @RequestBody MenuDto menuDto

    ){
        return new ResponseEntity<>(menuService.createMenu(menuDto),HttpStatus.CREATED);
    }
    //obtener un menu por su id
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @Operation(summary = "Obtener un menu por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Menu encontrado "),
            @ApiResponse(responseCode ="404",description = "Menu no encontrado",content = @Content)
    })

    @GetMapping("/menu/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable(value = "id") Long menuId){
        MenuDto menuDto = menuService.getMenuById(menuId);
        return new ResponseEntity<>(menuDto,HttpStatus.OK);
    }


    @Operation(summary = "Entrega los menus que puede ver un registeruser segun sus permisos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Menus encontrados "),
            @ApiResponse(responseCode ="404",description = "Menus no encontrados",content = @Content)
    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/menu/permiso/{idRegisteruser}")
    public List<MenuDto> EncontrarMenuSegunPermisos(
            @PathVariable(value ="idRegisteruser") Long idRegisteruser ){

        return menuService.EncontrarMenuSegunPermisos(idRegisteruser);

    }


    //actualizar un menu por su id
    @Operation(summary = "Actualizar un menu por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Menu actualizado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Menu no encontrado",content = @Content)

    })
    @PutMapping("/menu/{id}")
    public ResponseEntity<MenuDto> updateMenu(
            @PathVariable(value = "id") Long id,
            @RequestBody MenuDto menuDto
    ){
        MenuDto response=menuService.updateMenu(id,menuDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //Eliminar un menu por su id
    @Operation(summary = "ELiminar un menu por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Menu eliminado"),
            @ApiResponse(responseCode = "404",description = "Menu No encontrado",content = @Content)
    })
    @DeleteMapping("/menu/{id}")
    public ResponseEntity<String> deleteMenu(
            @PathVariable(name = "id") Long id
    ){
        menuService.deleteMenu(id);
        return new ResponseEntity<>("menu deleted successfull",HttpStatus.OK);

    }
}
