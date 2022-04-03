package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Perfil;
import com.springboot.inventario.entity.Permisos;
import com.springboot.inventario.entity.Usuario;
import com.springboot.inventario.payload.PermisosDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.function.BinaryOperator;

public interface PermisosRepository extends JpaRepository<Permisos,Long> {
    List<Permisos> findByPerfilId(Long PerfilId);

    @Query(value = "SELECT perfiles_permisos.* FROM perfiles_permisos WHERE perfiles_permisos.id_registeruser= ?1",
    nativeQuery = true)
    List<Permisos> ObtenerTodosLosPermisosDeUnRegisteruser(Long registeruserId);


    @Query(value = "SELECT perfiles_permisos.* FROM  perfiles_permisos WHERE perfiles_permisos.id_perfil=?1 AND perfiles_permisos.id_registeruser=?2",
    nativeQuery = true)
    List<Permisos> obenterTodosLospermisosDeUnPerfilyRegisteruser(Long perfilId,Long registeruserId);

}
