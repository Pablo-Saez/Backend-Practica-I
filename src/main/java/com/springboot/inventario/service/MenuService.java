package com.springboot.inventario.service;

import com.springboot.inventario.entity.Menu;
import com.springboot.inventario.payload.MenuDto;

import java.util.List;

public interface MenuService {
    MenuDto createMenu(MenuDto menuDto);

    MenuDto getMenuById(Long menuId);

    MenuDto updateMenu(Long menuId,MenuDto menuDto);

    void deleteMenu(long menuId);
    List<MenuDto> EncontrarMenuSegunPermisos(Long idRegisteruser);
}
