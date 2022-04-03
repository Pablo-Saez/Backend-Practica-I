package com.springboot.inventario.service;

import com.springboot.inventario.payload.APIResponse;
import com.springboot.inventario.payload.LoginDto;
import com.springboot.inventario.payload.RegisteruserDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    public APIResponse login(LoginDto loginDto);

    public RegisteruserDto login2(LoginDto loginDto);


}
