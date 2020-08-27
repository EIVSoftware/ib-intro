package com.ib.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ib.data.entity.UsuarioEntity;

@Repository
public class UsuarioRepository implements CrudRepository<UsuarioEntity> {

    private String SQL_SELECT = "SELECT * FROM usuarios";
    
    @Autowired
    private JdbcTemplate template;
    
    @Override
    public Iterable<UsuarioEntity> findAll() {
        
        return template.query(SQL_SELECT, (rs, i) -> {
            
            Long id = rs.getLong("usuario_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String apellidos = rs.getString("apellidos");
            String nombres = rs.getString("nombres");
            String email = rs.getString("email");
            Boolean accountNonExpired = rs.getBoolean("account_non_expired");
            Boolean accountNonLocked = rs.getBoolean("is_account_non_locked");
            Boolean credentialsNonExpired = rs.getBoolean("is_credentials_non_expired");
            Boolean enabled = rs.getBoolean("is_enabled");
            
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            
            usuarioEntity.setId(id);
            usuarioEntity.setUsername(username);
            usuarioEntity.setPassword(password);
            usuarioEntity.setNombres(nombres);
            usuarioEntity.setApellidos(apellidos);
            usuarioEntity.setEmail(email);
            usuarioEntity.setAccountNonExpired(accountNonExpired);
            usuarioEntity.setCredentialsNonExpired(credentialsNonExpired);
            usuarioEntity.setAccountNonLocked(accountNonLocked);
            usuarioEntity.setEnabled(enabled);
            
            return usuarioEntity;
        });
    }
}
