package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Menu;
import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.exception.EmailNotFoundException;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.payload.APIResponse;
import com.springboot.inventario.payload.LoginDto;
import com.springboot.inventario.payload.RegisteruserDto;
import com.springboot.inventario.repository.RegisteruserRepository;
import com.springboot.inventario.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    RegisteruserRepository registeruserRepository;

    public LoginServiceImpl(RegisteruserRepository registeruserRepository) {
        this.registeruserRepository = registeruserRepository;
    }


    public APIResponse login(LoginDto loginDto) {
        APIResponse apiResponse= new APIResponse();
        Registeruser registeruser= registeruserRepository.findOneByEmailIgnoreCaseAndPassword(loginDto.getEmail(),loginDto.getPassword());

        if(registeruser==null){
            apiResponse.setData("User login failed");
        }
        else{
            apiResponse.setData("User logged in");
        }
        return apiResponse;

    }

    @Override
    public RegisteruserDto login2(LoginDto loginDto) {
        Registeruser registeruser= registeruserRepository.findOneByEmailIgnoreCaseAndPassword(loginDto.getEmail(),loginDto.getPassword());
        return mapToDTO(registeruser);
    }
    //convert entity to Dto
    private RegisteruserDto mapToDTO(Registeruser registeruser){
        RegisteruserDto registeruserDto= new RegisteruserDto();
        registeruserDto.setId(registeruser.getId());
        registeruserDto.setName(registeruser.getName());
        registeruserDto.setLast_name(registeruser.getLast_name());
        registeruserDto.setEmail(registeruser.getEmail());
        registeruserDto.setCompany(registeruser.getCompany());
        registeruserDto.setPassword(registeruser.getPassword());

        registeruserDto.setRutcompany(registeruser.getRutcompany());
        registeruserDto.setIdkey(registeruser.getIdkey());
        registeruserDto.setValidate(registeruser.getValidate());
        registeruserDto.setLastaccessdate(registeruser.getLastaccessdate());
        registeruserDto.setTypelicense(registeruser.getTypelicense());
        registeruserDto.setDateexpiration(registeruser.getDateexpiration());
        registeruserDto.setNumberaccount(registeruser.getNumberaccount());
        registeruserDto.setActive(registeruser.getActive());
        registeruserDto.setParamettros(registeruser.getParamettros());

        return registeruserDto;

    }
    //convert dto to entity
    private Registeruser mapToEntity(RegisteruserDto registeruserDto){
        Registeruser registeruser=new Registeruser();
        // registeruser.setId(registeruserDto.getId());
        registeruser.setName(registeruserDto.getName());
        registeruser.setLast_name(registeruserDto.getLast_name());
        registeruser.setEmail(registeruserDto.getEmail());
        registeruser.setCompany(registeruserDto.getCompany());
        registeruser.setPassword(registeruserDto.getPassword());

        registeruser.setRutcompany(registeruserDto.getRutcompany());
        registeruser.setIdkey(registeruserDto.getIdkey());
        registeruser.setValidate(registeruserDto.getValidate());
        registeruser.setLastaccessdate(registeruserDto.getLastaccessdate());
        registeruser.setTypelicense(registeruserDto.getTypelicense());
        registeruser.setDateexpiration(registeruserDto.getDateexpiration());
        registeruser.setNumberaccount(registeruserDto.getNumberaccount());
        registeruser.setActive(registeruserDto.getActive());
        registeruser.setParamettros(registeruserDto.getParamettros());
        return registeruser;
    }


}
