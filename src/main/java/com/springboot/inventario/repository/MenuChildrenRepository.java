package com.springboot.inventario.repository;

import com.springboot.inventario.entity.MenuChildren;
import com.springboot.inventario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuChildrenRepository extends JpaRepository<MenuChildren,Long> {
    List<MenuChildren> findByMenuId(long MenuId);


    @Query(value = "SELECT menu_children.* FROM menu_children,perfiles_permisos WHERE menu_children.id=perfiles_permisos.id_permisos " +
            "AND perfiles_permisos.visible=true AND id_registeruser= ?1",
    nativeQuery = true)
    List<MenuChildren> EncontrarMenuChildrenConRegisteruserId(Long registeruserId);

    @Query(value = "SELECT menu_children.* FROM menu_children,perfiles_permisos WHERE menu_children.id=perfiles_permisos.id_permisos " +
            " AND id_registeruser= ?1",
            nativeQuery = true)
    List<MenuChildren> EncontrarTodosLosMenuChildrenConRegisteruserId(Long registeruserId);

}
