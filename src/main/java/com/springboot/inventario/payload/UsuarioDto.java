package com.springboot.inventario.payload;

import com.springboot.inventario.entity.Registeruser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
@Data
public class UsuarioDto {
    private Boolean active;
    private Long id;
    private String nombre;
    private String apellido;
    @NotEmpty
    @Size(min=5,max=150,message = "Email debe contener entre 5 y 150 caracteres")
    @Email
    private String email;
    @NotEmpty(message = "Email no puede estar vacio")
    @Size(min=5,max=150,message = "Contraseña debe contener entre 5 y 150 caracteres")
    private String password;
    private String rut;
    private String idkey;
    private String validatekey;
    private Date lastaccessdate;
    private int validate;
    private Long id_perfil;
    private String parametros;
    private Long id_registeruser;
    //private Long idperfil;

    //private Registeruser registeruser;

}
