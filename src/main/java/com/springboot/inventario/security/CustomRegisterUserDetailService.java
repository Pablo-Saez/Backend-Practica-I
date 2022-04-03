package com.springboot.inventario.security;

import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.entity.Role;
import com.springboot.inventario.repository.RegisteruserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
/* !!!
@Service
public class CustomRegisterUserDetailService implements UserDetailsService {

    private RegisteruserRepository registeruserRepository;

    public CustomRegisterUserDetailService(RegisteruserRepository registeruserRepository) {
        this.registeruserRepository = registeruserRepository;
    }
/* !!!
    @Override
    public UserDetails loadUserByUsername(String companyOrEmail) throws UsernameNotFoundException {
        Registeruser registeruser= registeruserRepository.findByCompanyOrEmail(companyOrEmail,companyOrEmail).orElseThrow(()->new UsernameNotFoundException("RegisterUser no encontrado con email: "+companyOrEmail));
        return new org.springframework.security.core.userdetails.User(registeruser.getEmail(),registeruser.getPassword(),mapRolesToAuthorities(registeruser.getRoles()));

    }



    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
*/