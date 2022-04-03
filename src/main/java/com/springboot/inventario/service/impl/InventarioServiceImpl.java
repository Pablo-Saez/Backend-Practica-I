package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Inventario;
import com.springboot.inventario.entity.Producto;
import com.springboot.inventario.payload.ProductoDto;
import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.payload.InventarioDto;
import com.springboot.inventario.payload.ProductoDto;
import com.springboot.inventario.repository.InventarioRepository;
import com.springboot.inventario.repository.RegisteruserRepository;
import com.springboot.inventario.service.InventarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioServiceImpl implements InventarioService {
    private InventarioRepository inventarioRepository;
    private RegisteruserRepository registeruserRepository;

    public InventarioServiceImpl(InventarioRepository inventarioRepository, RegisteruserRepository registeruserRepository) {
        this.inventarioRepository = inventarioRepository;
        this.registeruserRepository = registeruserRepository;
    }


    @Override
    public InventarioDto createInventario(Long id,InventarioDto inventarioDto) {
        boolean estarepetido=false;
        List<Inventario> inventarios = inventarioRepository.findByRegisteruserId(id);
        inventarios.stream().map(inventario -> mapToDto(inventario)).collect(Collectors.toList());
        for(int i=0;i<inventarios.size();i++){
            if(inventarioDto.getNombre()==inventarios.get(i).getNombre()){
                estarepetido=true;
                break;
            }
        }
        if(!estarepetido) {
            Inventario inventario = mapToEntity(inventarioDto);
            Registeruser registeruser = registeruserRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Registeruser", "id", id));
            inventario.setRegisteruser(registeruser);
            Inventario newInventario = inventarioRepository.save(inventario);
            return mapToDto(newInventario);
        }
        else {
            return null;
        }


    }

    @Override
    public List<InventarioDto> obtenerInventariosConRegisteruserId(long id) {
        List<Inventario> inventarios = inventarioRepository.findByRegisteruserId(id);
        return inventarios.stream().map(inventario -> mapToDto(inventario)).collect(Collectors.toList());
    }

    @Override
    public List<InventarioDto> obtenerTodosLosInventariosSinId() {
        List<Inventario> inventarios = inventarioRepository.findAll();
        return inventarios.stream().map(inventario -> mapToDto(inventario)).collect(Collectors.toList());

    }

    @Override
    public InventarioDto actualizarInventario(Long id, InventarioDto inventarioDto) {
        Inventario inventario = inventarioRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Inventario","id",id));
        if(inventarioDto.getTipo()!=null)
        inventario.setTipo(inventarioDto.getTipo());
        if(inventarioDto.getUbicacion()!=null)
        inventario.setUbicacion(inventarioDto.getUbicacion());
        if(inventarioDto.getNombre()!=null)
        inventario.setNombre(inventarioDto.getNombre());

        Inventario inventarioActualizado = inventarioRepository.save(inventario);
        return mapToDto(inventarioActualizado);
    }



    @Override
    public void deleteInventario(Long id) {
        Inventario inventario = inventarioRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Inventario","id",id));

        inventarioRepository.delete(inventario);
    }



    private InventarioDto mapToDto(Inventario inventario){
        InventarioDto inventarioDto = new InventarioDto();
        inventarioDto.setId(inventario.getId());
        inventarioDto.setNombre(inventario.getNombre());
        inventarioDto.setTipo(inventario.getTipo());
        inventarioDto.setUbicacion(inventario.getUbicacion());
        inventarioDto.setId_registeruser(inventario.getRegisteruser().getId());
        return inventarioDto;

    }
    private Inventario mapToEntity(InventarioDto inventarioDto){
        Inventario inventario= new Inventario();
        //inventario.setId(inventarioDto.getId());
        inventario.setNombre(inventarioDto.getNombre());
        inventario.setTipo(inventarioDto.getTipo());
        inventario.setUbicacion(inventarioDto.getUbicacion());
        return inventario;
    }
}
