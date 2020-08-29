package com.ib.data.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ib.data.entity.UsuarioEntity;

@Repository
public class UsuarioRepository implements CrudRepository<UsuarioEntity, Long> {

    private static final String SQL_SELECT_ALL = 
            "SELECT * FROM usuarios";
    
    private static final String SQL_SELECT_BY_ID = 
            "SELECT * FROM usuarios WHERE usuario_id = :id";
    
    private static final String SQL_SELECT_BY_USRNAME = 
            "SELECT * FROM usuarios WHERE username = :username";
    
    @Autowired
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Iterable<UsuarioEntity> findAll() {
        
        JdbcTemplate template = new JdbcTemplate(dataSource);
        
        return template.query(SQL_SELECT_ALL, (rs, i) -> {
            UsuarioEntity usuarioEntity = extract(rs);
            return usuarioEntity;
        });
    }

    @Override
    public Optional<UsuarioEntity> findById(Long id) {
        
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        Optional<UsuarioEntity> optional = template.query(
                SQL_SELECT_BY_ID, 
                parameterSource, 
                resultSetExtractor());
        
        return optional;
    }

    public Optional<UsuarioEntity> findByUsername(String username) {
        
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);
        
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        Optional<UsuarioEntity> optional = template.query(
                SQL_SELECT_BY_USRNAME, 
                parameterSource, 
                resultSetExtractor());
        
        return optional;
    }
    
    private ResultSetExtractor<Optional<UsuarioEntity>> resultSetExtractor() {
        return new ResultSetExtractor<Optional<UsuarioEntity>>() {
            @Override
            public Optional<UsuarioEntity> extractData(ResultSet rs) 
                    throws SQLException, DataAccessException {
                
                if (rs.next()) {
                    UsuarioEntity usuarioEntity = extract(rs);
                    return Optional.of(usuarioEntity);
                }
                
                return Optional.empty();
            }
        };
    }
    
    private UsuarioEntity extract(final ResultSet rs) throws SQLException {

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
    }
}
