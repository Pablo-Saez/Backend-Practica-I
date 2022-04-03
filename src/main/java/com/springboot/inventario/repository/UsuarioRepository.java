package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByRegisteruserId(long registeruserId);
}
