package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Menu;
import com.springboot.inventario.entity.MenuChildren;
import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.payload.MenuChildrenDto;
import com.springboot.inventario.repository.MenuChildrenRepository;
import com.springboot.inventario.repository.MenuRepository;
import com.springboot.inventario.service.MenuChildrenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuChildrenServiceImpl implements MenuChildrenService {
    private MenuChildrenRepository menuChildrenRepository;
    private MenuRepository menuRepository;

    public MenuChildrenServiceImpl(MenuChildrenRepository menuChildrenRepository, MenuRepository menuRepository) {
        this.menuChildrenRepository = menuChildrenRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuChildrenDto createMenuChildren(Long menuId, MenuChildrenDto menuChildrenDto) {
        MenuChildren menuChildren=mapToEntity(menuChildrenDto);
        Menu menu=menuRepository.findById(menuId).orElseThrow(
                ()-> new ResourceNotFoundException("Menu","id",menuId));
        menuChildren.setMenu(menu);
        MenuChildren newMenu=menuChildrenRepository.save(menuChildren);
        return mapToDto(newMenu);
    }

    @Override
    public MenuChildrenDto getMenuChildren(Long idMenuChildren) {
        MenuChildren menuChildren=menuChildrenRepository.findById(idMenuChildren).orElseThrow(()->
                new ResourceNotFoundException("MenuChildren","id",idMenuChildren));
        return mapToDto(menuChildren);


    }

    @Override
    public List<MenuChildrenDto> getMenuChildrenByMenuId(long idMenu) {
        List<MenuChildren> menuChildrens = menuChildrenRepository.findByMenuId(idMenu);
        return menuChildrens.stream().map(menuChildren -> mapToDto(menuChildren)).collect(Collectors.toList());
    }

    @Override
    public MenuChildrenDto updateMenuChildren(Long idMenuChildren, MenuChildrenDto menuChildrenDto) {
        MenuChildren menuChildren=menuChildrenRepository.findById(idMenuChildren).orElseThrow(()->
                new ResourceNotFoundException("MenuChildren","id",idMenuChildren));
        menuChildren.setTitle(menuChildrenDto.getTitle());
        menuChildren.setComponent(menuChildrenDto.getComponent());
        menuChildrenDto.setPath(menuChildrenDto.getPath());
        menuChildren.setIcon(menuChildrenDto.getIcon());
        menuChildren.setEstado(menuChildrenDto.getEstado());
        menuChildren.setOrden(menuChildrenDto.getOrden());
        menuChildren.setApp(menuChildrenDto.getApp());
        menuChildren.setMovil(menuChildrenDto.getMovil());
        MenuChildren updatedMenuChildren = menuChildrenRepository.save(menuChildren);
        return mapToDto(updatedMenuChildren);
    }

    @Override
    public void deleteMenuChildren(Long idMenuChildren) {
        MenuChildren menuChildren=menuChildrenRepository.findById(idMenuChildren).orElseThrow(()->
                new ResourceNotFoundException("MenuChildren","id",idMenuChildren));
        menuChildrenRepository.delete(menuChildren);
    }

    @Override
    public List<MenuChildrenDto> EncontrarMenuChildrenConRegisteruserId(Long registeruserId) {
        List<MenuChildren> menuChildrenList= menuChildrenRepository.EncontrarMenuChildrenConRegisteruserId(registeruserId);
        return menuChildrenList.stream().map(menuChildren -> mapToDto(menuChildren)).collect(Collectors.toList());
    }

    @Override
    public List<MenuChildrenDto> EncontrarTodosLosMenuChildrenConRegisteruserId(Long registeruserId) {
        List<MenuChildren> menuChildrenList=menuChildrenRepository.EncontrarTodosLosMenuChildrenConRegisteruserId(registeruserId);
        return menuChildrenList.stream().map(menuChildren -> mapToDto(menuChildren)).collect(Collectors.toList());
    }


    private MenuChildrenDto mapToDto(MenuChildren menuChildren){
        MenuChildrenDto menuChildrenDto = new MenuChildrenDto();
        menuChildrenDto.setId(menuChildren.getId());
        menuChildrenDto.setTitle(menuChildren.getTitle());
        menuChildrenDto.setComponent(menuChildren.getComponent());
        menuChildrenDto.setPath(menuChildren.getPath());
        menuChildrenDto.setIcon(menuChildren.getIcon());
        menuChildrenDto.setEstado(menuChildren.getEstado());
        menuChildrenDto.setOrden(menuChildren.getOrden());
        menuChildrenDto.setApp(menuChildren.getApp());
        menuChildrenDto.setMovil(menuChildren.getMovil());
        menuChildrenDto.setId_menu(menuChildren.getMenu().getId());
        return menuChildrenDto;
    }
    private MenuChildren mapToEntity(MenuChildrenDto menuChildrenDto){
        MenuChildren menuChildren= new MenuChildren();
        menuChildren.setTitle(menuChildrenDto.getTitle());
        menuChildren.setComponent(menuChildrenDto.getComponent());
        menuChildren.setPath(menuChildrenDto.getPath());
        menuChildren.setIcon(menuChildrenDto.getIcon());
        menuChildren.setEstado(menuChildrenDto.getEstado());
        menuChildren.setOrden(menuChildrenDto.getOrden());
        menuChildren.setApp(menuChildrenDto.getApp());
        menuChildren.setMovil(menuChildrenDto.getMovil());
        return menuChildren;
    }
}
