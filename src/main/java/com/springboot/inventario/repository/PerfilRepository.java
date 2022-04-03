package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Perfil;
import com.springboot.inventario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {
    List<Perfil> findByRegisteruserId(Long registeruserId);


    @Query(value = "SELECT perfiles.* FROM perfiles WHERE perfiles.idregisteruser=?1 AND perfiles.visible=true",
    nativeQuery = true)
    List<Perfil> EncontrarPerfilesDeRegisteruser(Long registeruserId);
}
