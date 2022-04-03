package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.payload.RegisteruserDto;
import com.springboot.inventario.payload.RegisteruserResponse;
import com.springboot.inventario.repository.RegisteruserRepository;
import com.springboot.inventario.service.RegisteruserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisteruserserviceImpl implements RegisteruserService {
    private RegisteruserRepository registeruserRepository;

    public RegisteruserserviceImpl(RegisteruserRepository registeruserRepository) {
        this.registeruserRepository = registeruserRepository;
    }
    //CREATE A REGISTERUSER
    @Override
    public RegisteruserDto createRegisteruser(RegisteruserDto registeruserDto){
        //convert DTO to entity
        Registeruser registeruser=mapToEntity(registeruserDto);
        Registeruser newRegisteruser =registeruserRepository.save(registeruser);

        //convert entity to DTO
        return mapToDTO(newRegisteruser);

    }

    @Override
    public RegisteruserResponse getAllRegisterusers(int pageNo,int pageSize, String sortBy,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        //create pageable instance
        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        Page<Registeruser> registerusers=registeruserRepository.findAll(pageable);
        //get content for the page object
        List<Registeruser> listOfRegisteruser= registerusers.getContent();
        List<RegisteruserDto> content =listOfRegisteruser.stream().map(registeruser -> mapToDTO(registeruser)).collect(Collectors.toList());
        RegisteruserResponse registeruserResponse = new RegisteruserResponse();
        registeruserResponse.setUsers(content);
        registeruserResponse.setPageNo(registerusers.getNumber());
        registeruserResponse.setPageSize(registerusers.getSize());
        registeruserResponse.setTotalElements(registerusers.getTotalElements());
        registeruserResponse.setTotalPages(registerusers.getTotalPages());
        registeruserResponse.setLast(registerusers.isLast());

        return registeruserResponse;

    }

    @Override
    public RegisteruserDto getRegisteruserById(Long id) {
        Registeruser registeruser = registeruserRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Registeruser","id",id));

        return mapToDTO(registeruser);
    }

    @Override
    public RegisteruserDto getRegisteruserByEmail(String email, String password) {
        List<Registeruser> registerusers = registeruserRepository.findAll();
        RegisteruserDto registeruserDto=new RegisteruserDto();
        for(int i=0;i<registerusers.size();i++){
            if(registerusers.get(i).getEmail()==email && registerusers.get(i).getPassword()==password){
                registeruserDto=mapToDTO(registerusers.get(i));
                return registeruserDto;
            }
        }
        return registeruserDto;
    }

    @Override
    public RegisteruserDto updateRegisteruser(RegisteruserDto registeruserDto, long id) {
        //get port by id from the database
        Registeruser registeruser = registeruserRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Registeruser","id",id));
        if(registeruserDto.getName()!=null)
        registeruser.setName(registeruserDto.getName());
        if(registeruserDto.getLast_name()!=null)
        registeruser.setLast_name(registeruserDto.getLast_name());
        if(registeruserDto.getEmail()!=null)
        registeruser.setEmail(registeruserDto.getEmail());
        if(registeruserDto.getCompany()!=null)
        registeruser.setCompany(registeruserDto.getCompany());
        if(registeruserDto.getPassword()!=null)
        registeruser.setPassword(registeruserDto.getPassword());

        if(registeruserDto.getRutcompany()!=null)
        registeruser.setRutcompany(registeruserDto.getRutcompany());
        if(registeruserDto.getIdkey()!=null)
        registeruser.setIdkey(registeruserDto.getIdkey());
        if(registeruserDto.getValidate()!=0)
        registeruser.setValidate(registeruserDto.getValidate());
        if(registeruserDto.getLastaccessdate()!=null)
        registeruser.setLastaccessdate(registeruserDto.getLastaccessdate());
        if(registeruserDto.getTypelicense()!=0)
        registeruser.setTypelicense(registeruserDto.getTypelicense());
        if(registeruserDto.getDateexpiration()!=null)
        registeruser.setDateexpiration(registeruserDto.getDateexpiration());
        if(registeruserDto.getNumberaccount()!=0)
        registeruser.setNumberaccount(registeruserDto.getNumberaccount());
        if(registeruserDto.getActive()!=null)
        registeruser.setActive(registeruserDto.getActive());
        if(registeruserDto.getParamettros()!=null)
        registeruser.setParamettros(registeruserDto.getParamettros());
        Registeruser updateRegisteruser = registeruserRepository.save(registeruser);
        return mapToDTO(updateRegisteruser);

    }

    @Override
    public void deleteRegisteruserById(long id) {
        Registeruser registeruser = registeruserRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Registeruser","id",id)); //verifica si existe y si no arroja la excepcion
        registeruserRepository.delete(registeruser);
    }


    //convert entity to DTO
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
