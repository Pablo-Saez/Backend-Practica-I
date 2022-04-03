package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Menu;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.payload.MenuDto;
import com.springboot.inventario.repository.MenuRepository;
import com.springboot.inventario.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


    @Override
    public MenuDto createMenu(MenuDto menuDto) {
        Menu menu=mapToEntity(menuDto);
        Menu newMenu=menuRepository.save(menu);

        MenuDto menuResponse=mapToDto(newMenu);
        return menuResponse;
    }

    @Override
    public MenuDto getMenuById(Long menuId) {
        Menu menu=menuRepository.findById(menuId).orElseThrow(()->
                new ResourceNotFoundException("Menu","id",menuId));
        return mapToDto(menu);
    }

    @Override
    public MenuDto updateMenu(Long menuId, MenuDto menuDto) {
        Menu menu=menuRepository.findById(menuId).orElseThrow(()->
                new ResourceNotFoundException("Menu","id",menuId));
        if(menuDto.getTitle()!=null)
        menu.setTitle(menuDto.getTitle());
        if(menuDto.getRedirect()!=null)
        menu.setRedirect(menuDto.getRedirect());
        if(menuDto.getComponent()!=null)
        menu.setComponent(menuDto.getComponent());
        if(menuDto.getPath()!=null)
        menu.setPath(menuDto.getPath());
        if(menuDto.getName()!=null)
        menu.setName(menuDto.getName());
        if(menuDto.getIcon()!=null)
        menu.setIcon(menuDto.getIcon());
        if(menuDto.getEstado()!=null)
        menu.setEstado(menuDto.getEstado());
        if(menuDto.getOrden()!=0)
        menu.setOrden(menuDto.getOrden());
        if(menuDto.getApp()!=null)
        menu.setApp(menuDto.getApp());
        if(menuDto.getMovil()!=0)
        menu.setMovil(menuDto.getMovil());
        Menu updatedMenu = menuRepository.save(menu);
        return mapToDto(updatedMenu);
    }

    @Override
    public void deleteMenu(long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(()->
                new ResourceNotFoundException("Menu","id",menuId));
        menuRepository.delete(menu);
    }

    @Override
    public List<MenuDto> EncontrarMenuSegunPermisos(Long idRegisteruser) {
       List<Menu> menus = menuRepository.EncontrarMenuSegunPermisos(idRegisteruser);
       return menus.stream().map(menu -> mapToDto(menu)).collect(Collectors.toList());
    }


    private MenuDto mapToDto(Menu menu){
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setTitle(menu.getTitle());
        menuDto.setRedirect(menu.getRedirect());
        menuDto.setComponent(menu.getComponent());
        menuDto.setPath(menu.getPath());
        menuDto.setName(menu.getName());
        menuDto.setIcon(menu.getIcon());
        menuDto.setEstado(menu.getEstado());
        menuDto.setOrden(menu.getOrden());
        menuDto.setApp(menu.getApp());
        menuDto.setMovil(menuDto.getMovil());
        return menuDto;
    }
    private Menu mapToEntity(MenuDto menuDto){
        Menu menu = new Menu();
        menu.setTitle(menuDto.getTitle());
        menu.setRedirect(menuDto.getRedirect());
        menu.setComponent(menuDto.getComponent());
        menu.setPath(menuDto.getPath());
        menu.setName(menuDto.getName());
        menu.setIcon(menuDto.getIcon());
        menu.setEstado(menuDto.getEstado());
        menu.setOrden(menuDto.getOrden());
        menu.setApp(menuDto.getApp());
        menu.setMovil(menuDto.getMovil());
        return menu;
    }
}
