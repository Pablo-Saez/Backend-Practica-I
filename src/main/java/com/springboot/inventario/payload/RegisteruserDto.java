package com.springboot.inventario.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
@Data
public class RegisteruserDto {
    private Long id;
    private String name;
    private String last_name;

    @NotEmpty(message = "Email no puede estar vacio")
    @Size(min=5,max=150,message = "Email debe contener entre 5 y 150 caracteres")
    @Email
    private String email;
    private String rutcompany;
    private String company;
    @NotEmpty
    @Size(min=5,max=150,message = "Contraseña debe contener entre 5 y 150 caracteres")
    private String password;
    private String idkey;
    private int validate;
    private Date createdate;
    private Date validatedate;
    private Date lastaccessdate;
    private int typelicense;
    private Date dateexpiration;
    private int numberaccount;
    private Boolean active;
    private String paramettros;

}
