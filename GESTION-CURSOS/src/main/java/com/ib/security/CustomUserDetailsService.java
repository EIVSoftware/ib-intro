package com.ib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ib.data.entity.UsuarioEntity;
import com.ib.data.service.UsuarioService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        final UsuarioEntity usuarioEntity = usuarioService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("EL NOMBRE DE USUARIO %s NO EXISTE!", username)));
        
        return new Usuario(usuarioEntity);
    }
}
