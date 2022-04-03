package com.springboot.inventario.controller;

import com.springboot.inventario.entity.MenuChildren;
import com.springboot.inventario.payload.MenuChildrenDto;
import com.springboot.inventario.service.MenuChildrenService;
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
public class MenuChildrenController {
    private MenuChildrenService menuChildrenService;
    public MenuChildrenController(MenuChildrenService menuChildrenService) {
        this.menuChildrenService = menuChildrenService;
    }
    //Crear un menuChildren
    @Operation(summary = "Crear un menuChildren" )
    @ApiResponses(value = {
            @ApiResponse(responseCode="201",description = "MenuChildren guardado"),
            @ApiResponse(responseCode = "500",description = "Error al guardar menuChildren",content = @Content),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)

    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping("/menu/{idMenu}/menuChildren")
    public ResponseEntity<MenuChildrenDto> createMenuChildren(
            @PathVariable(value = "idMenu") Long idMenu,
            @RequestBody MenuChildrenDto menuChildrenDto
    ){
        return new ResponseEntity<>(menuChildrenService.createMenuChildren(idMenu,menuChildrenDto), HttpStatus.CREATED);
    }
    //obtener un menuChildren por su id
    @Operation(summary = "Obtener un menuChildren por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "MenuChildren encontrado "),
            @ApiResponse(responseCode ="404",description = "MenuChildren no encontrado",content = @Content)
    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/menuChildren/{id}")
    public ResponseEntity<MenuChildrenDto> getMenuChildren(
        @PathVariable(value = "id") Long id
    ){
        MenuChildrenDto menuChildrenDto=menuChildrenService.getMenuChildren(id);
        return new ResponseEntity<>(menuChildrenDto,HttpStatus.OK);
    }
    //obtener todos los menusChildren dado un menu
    @Operation(summary = "Obtener todos los menusChildren dado un menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Menus encontrados "),
            @ApiResponse(responseCode ="404",description = "Menus no encontrados",content = @Content)
    })
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/menu/{id}/menuChildren")
    public List<MenuChildrenDto> getAllMenuChildrenByMenuId(@PathVariable (value = "id") Long menuId){
        return menuChildrenService.getMenuChildrenByMenuId(menuId);
    }
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/menuchildrens/registeruser/{registeruserId}")
    public List<MenuChildrenDto> EncontrarMenuChildrenConRegisteruserId(@PathVariable(value = "registeruserId")Long registeruserId){
        return menuChildrenService.EncontrarMenuChildrenConRegisteruserId(registeruserId);
    }
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/menuchildrens/all/registeruser/{registeruserId}")
    public List<MenuChildrenDto> EncontrarTodosLosMenuChildrenConRegisteruserId(@PathVariable(value = "registeruserId") Long registeruserId){
        return menuChildrenService.EncontrarTodosLosMenuChildrenConRegisteruserId(registeruserId);
    }
    //actualizar un menuChildren
    @Operation(summary = "Actualizar un menuChildren")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "MenuChildren actualizado"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "MenuChildren no encontrado",content = @Content)

    })
    @PutMapping("/menuChildren/{id}")
    public ResponseEntity<MenuChildrenDto> updateMenuChildren(
        @PathVariable(value = "id") Long id,
        @RequestBody MenuChildrenDto menuChildrenDto
    ){
        MenuChildrenDto response=menuChildrenService.updateMenuChildren(id,menuChildrenDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //Eliminar un menuChildren por su id
    @Operation(summary = "Eliminar MenuChildren por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "MenuChildren eliminado"),
            @ApiResponse(responseCode = "404",description = "MenuChildren No encontrado",content = @Content)
    })
    @DeleteMapping("/menuChildren/{id}")
    public ResponseEntity<String> deleteMenuChildren(
            @PathVariable(value = "id") Long id
    ){
        menuChildrenService.deleteMenuChildren(id);
        return new ResponseEntity<>("menuChildren deleted successfull",HttpStatus.OK);
    }

}
