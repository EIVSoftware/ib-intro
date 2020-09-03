package com.ib.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ib.data.entity.UsuarioEntity;

public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;
    
    private UsuarioEntity usuarioEntity;
    
    public Usuario(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(
                new SimpleGrantedAuthority("ADMINISTRADOR"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return usuarioEntity.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return usuarioEntity.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return usuarioEntity.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return usuarioEntity.getEnabled();
    }

    @Override
    public String getPassword() {
        return usuarioEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return usuarioEntity.getUsername();
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }
}
