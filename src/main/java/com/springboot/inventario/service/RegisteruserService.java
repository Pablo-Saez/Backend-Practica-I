package com.springboot.inventario.service;

import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.payload.RegisteruserDto;
import com.springboot.inventario.payload.RegisteruserResponse;

import java.util.List;

public interface RegisteruserService {
    RegisteruserDto createRegisteruser(RegisteruserDto registeruserDto);

    RegisteruserResponse getAllRegisterusers(int pageNo, int pageSize, String sortBy,String sortDir);

    RegisteruserDto getRegisteruserById(Long id);

    RegisteruserDto getRegisteruserByEmail(String email,String password);

    RegisteruserDto updateRegisteruser(RegisteruserDto registeruserDto,long id);

    void deleteRegisteruserById(long id);
}
