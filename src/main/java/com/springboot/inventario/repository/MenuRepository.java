package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    @Query(value = "SELECT DISTINCT menu.* FROM menu,perfiles_permisos WHERE " +
            "menu.id=perfiles_permisos.id_menu AND perfiles_permisos.visible=true AND " +
            "perfiles_permisos.id_registeruser=?1 ",
    nativeQuery = true)
    List<Menu> EncontrarMenuSegunPermisos(Long IdRegisteruser);


}
