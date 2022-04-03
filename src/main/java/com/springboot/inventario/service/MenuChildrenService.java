package com.springboot.inventario.service;

import com.springboot.inventario.entity.MenuChildren;
import com.springboot.inventario.payload.MenuChildrenDto;
import com.springboot.inventario.payload.UsuarioDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuChildrenService {
    MenuChildrenDto createMenuChildren(Long Idmenu,MenuChildrenDto menuChildrenDto);

    MenuChildrenDto getMenuChildren(Long idMenuChildren);

    List<MenuChildrenDto> getMenuChildrenByMenuId(long idMenu);

    MenuChildrenDto updateMenuChildren(Long idMenuChildren,MenuChildrenDto menuChildrenDto);

    void deleteMenuChildren(Long idMenuChildren);

    List<MenuChildrenDto> EncontrarMenuChildrenConRegisteruserId(Long registeruserId);

    List<MenuChildrenDto> EncontrarTodosLosMenuChildrenConRegisteruserId(Long registeruserId);
}
