package com.springboot.inventario.controller;

import com.springboot.inventario.payload.RegisteruserDto;
import com.springboot.inventario.payload.RegisteruserResponse;
import com.springboot.inventario.service.RegisteruserService;
import com.springboot.inventario.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/web")
public class RegisteruserController {
    private RegisteruserService registeruserService;

    public RegisteruserController(RegisteruserService registeruserService) {
        this.registeruserService = registeruserService;
    }
    //crear un registeruser

    @Operation(summary = "Crear un registeruser" )
    @ApiResponses(value = {
            @ApiResponse(responseCode="201",description = "Registeruser guardado"),
            @ApiResponse(responseCode = "500",description = "Error al guardar registeruser",content = @Content),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)

    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RegisteruserDto> createRegisteruser(@Valid @RequestBody RegisteruserDto registeruserDto){
        return new ResponseEntity<>(registeruserService.createRegisteruser(registeruserDto), HttpStatus.CREATED);
    }

    //get all registerusers
    @Operation(summary = "Obtener todos los registerusers en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Registeruser encontrado "),
            @ApiResponse(responseCode ="404",description = "Registeruser no encontrado",content = @Content)
    })

    @GetMapping
    public RegisteruserResponse gettAllRegisterusers(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue= AppConstants.DEGAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return registeruserService.getAllRegisterusers(pageNo,pageSize,sortBy,sortDir);
    }
   /* @GetMapping("/validar")
    public ResponseEntity<RegisteruserDto> validar(String email,String password){

    }*/

    //get a registeruser by id
    @Operation(summary = "Obtener un registeruser por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Registeruser encontrado "),
            @ApiResponse(responseCode ="404",description = "Registeruser no encontrado",content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RegisteruserDto> getRegisteruserById(@PathVariable(name="id") long id){
        return ResponseEntity.ok(registeruserService.getRegisteruserById(id));
    }

    //update post by id
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar un registeruser por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Registeruser actualizado "),
            @ApiResponse(responseCode ="400",description = "Bad Request",content = @Content),
            @ApiResponse(responseCode = "404",description = "Registeruser No encontrado",content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<RegisteruserDto> updateRegisteruser(@Valid @RequestBody RegisteruserDto registeruserDto,@PathVariable(name = "id") long id){
        RegisteruserDto response= registeruserService.updateRegisteruser(registeruserDto,id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //delete registeruser by id
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar un registeruser por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Registeruser eliminado"),
            @ApiResponse(responseCode = "404",description = "Registeruser No encontrado",content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegisteruser(@PathVariable(name = "id")long id){
        registeruserService.deleteRegisteruserById(id);
        return new ResponseEntity<>("Registeruser deleted successfullt",HttpStatus.OK);
    }

}
