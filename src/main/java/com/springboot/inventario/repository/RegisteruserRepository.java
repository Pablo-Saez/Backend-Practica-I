package com.springboot.inventario.repository;

import com.springboot.inventario.entity.Registeruser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisteruserRepository extends JpaRepository<Registeruser,Long> {
    Optional<Registeruser> findByEmail(String email);
    Optional<Registeruser> findByCompanyOrEmail(String company,String email);
    Optional<Registeruser> findByCompany(String company);
    Boolean existsByCompany(String company);
    Boolean existsByEmail(String email);


    Registeruser findOneByEmailIgnoreCaseAndPassword(String email, String password);
}
