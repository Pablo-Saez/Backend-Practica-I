package com.springboot.inventario.controller;


import com.springboot.inventario.payload.APIResponse;
import com.springboot.inventario.payload.LoginDto;
import com.springboot.inventario.payload.RegisteruserDto;
import com.springboot.inventario.service.LoginService;
import com.springboot.inventario.service.RegisteruserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api")
public class AuthController {
    private LoginService loginService;
    private RegisteruserService registeruserService;
    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }
    @Operation(summary = "Login de un registeruser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "logged in"),
            @ApiResponse(responseCode ="400",description = "Bad request",content = @Content),
            @ApiResponse(responseCode ="404",description = "Datos no validos",content = @Content)

    })
    @PostMapping("/login")
   public ResponseEntity<RegisteruserDto> login(@RequestBody LoginDto loginDto){
       /*APIResponse apiResponse= loginService.login(loginDto);
       return  ResponseEntity
               .status(apiResponse.getStatus())
               .body(apiResponse);*/
      RegisteruserDto registeruserDto = loginService.login2(loginDto);
      return new ResponseEntity<>(registeruserDto,HttpStatus.OK);
   }
   /* @PostMapping("/login2")
    public ResponseEntity<APIResponse> login3(@RequestBody LoginDto loginDto){
        APIResponse apiResponse= loginService.login(loginDto);
        return  ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

   @PostMapping("/signup")
    public ResponseEntity<RegisteruserDto> login2 (@RequestBody LoginDto loginDto){
        RegisteruserDto registeruserDto = loginService.login2(loginDto);
        return new ResponseEntity<>(registeruserDto,HttpStatus.OK);
   }

*/
    /*@Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Credenciales correctas!", HttpStatus.OK);




    }

      */
}
