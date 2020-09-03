package com.ib.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ib.data.dto.Usuario;
import com.ib.data.entity.UsuarioEntity;
import com.ib.data.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired private UsuarioRepository usuarioRepository;
    
    public List<UsuarioEntity> findAll() {
        return (List<UsuarioEntity>) usuarioRepository.findAll();
    }
    
    public Optional<UsuarioEntity> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioEntity> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    
    public UsuarioEntity create(Usuario usuario) {
        
        final long id = usuarioRepository.nextId();
        final String username = usuario.getUsername();
        final String password = toBcrypt(usuario.getPassword());
        final String apellidos = usuario.getApellidos();
        final String nombres = usuario.getNombres();
        final String email = usuario.getEmail();
        
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        
        usuarioEntity.setId(id);
        usuarioEntity.setUsername(username);
        usuarioEntity.setPassword(password);
        usuarioEntity.setApellidos(apellidos);
        usuarioEntity.setNombres(nombres);
        usuarioEntity.setEmail(email);
        usuarioEntity.setAccountNonExpired(true);
        usuarioEntity.setAccountNonLocked(true);
        usuarioEntity.setCredentialsNonExpired(true);
        usuarioEntity.setEnabled(true);
        
        usuarioRepository.create(usuarioEntity);
        
        return usuarioEntity;
    }

    public UsuarioEntity update(Long id, Usuario usuario) {
        
        final String username = usuario.getUsername();
        final String password = toBcrypt(usuario.getPassword());
        final String apellidos = usuario.getApellidos();
        final String nombres = usuario.getNombres();
        final String email = usuario.getEmail();
        final Boolean accountNonExpired = usuario.getAccountNonExpired();
        final Boolean accountNonLocked = usuario.getAccountNonLocked();
        final Boolean credentialsNonExpired = usuario.getCredentialsNonExpired();
        final Boolean enabled = usuario.getEnabled();
        
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        
        usuarioEntity.setId(id);
        usuarioEntity.setUsername(username);
        usuarioEntity.setPassword(password);
        usuarioEntity.setApellidos(apellidos);
        usuarioEntity.setNombres(nombres);
        usuarioEntity.setEmail(email);
        usuarioEntity.setAccountNonExpired(accountNonExpired);
        usuarioEntity.setAccountNonLocked(accountNonLocked);
        usuarioEntity.setCredentialsNonExpired(credentialsNonExpired);
        usuarioEntity.setEnabled(enabled);
        
        usuarioRepository.update(id, usuarioEntity);
        
        return usuarioEntity;
    }
    
    public void delete(Long id) {
        usuarioRepository.delete(id);
    }
    
    private String toBcrypt(String password) {
        // TODO FALTA IMPLEMENTARSE
        return password;
    }
    
}
