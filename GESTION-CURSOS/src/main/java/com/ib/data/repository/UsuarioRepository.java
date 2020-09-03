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

    private static final String SQL_SELECT_MAX_ID = 
            "SELECT MAX(usuario_id) FROM usuarios";
    
    private static final String SQL_SELECT_ALL = 
            "SELECT * FROM usuarios";
    
    private static final String SQL_SELECT_BY_ID = 
            "SELECT * FROM usuarios WHERE usuario_id = :id";
    
    private static final String SQL_SELECT_BY_USRNAME = 
            "SELECT * FROM usuarios WHERE username = :username";
    
    private static final String SQL_INSERT = 
            "INSERT INTO usuarios ("
            + "usuario_id, username, password, apellidos, nombres, email, account_non_expired, "
            + "is_account_non_locked, is_credentials_non_expired, is_enabled) "
            + "VALUES ("
            + ":usuarioId, :username, :password, :apellidos, :nombres, :email, "
            + ":accountNonExpired, :accountNonLocked, :credentialsNonExpired, :enabled)";

    private static final String SQL_UPDATE = 
            "UPDATE usuarios SET "
            + "username = :username, "
            + "password = :password, "
            + "apellidos = :apellidos, "
            + "nombres = :nombres, "
            + "email = :email, "
            + "account_non_expired = :accountNonExpired, "
            + "is_account_non_locked = :accountNonLocked, "
            + "is_credentials_non_expired = :credentialsNonExpired, "
            + "is_enabled = :enabled "
            + "WHERE usuario_id = :id";

    private static final String SQL_DELETE = 
            "DELETE usuarios WHERE usuario_id = :id";
    
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
    
    @Override
    public Long nextId() {

        JdbcTemplate template = new JdbcTemplate(dataSource);
        Long maxId = template.query(SQL_SELECT_MAX_ID, new ResultSetExtractor<Long>() {

            @Override
            public Long extractData(ResultSet rs) 
                    throws SQLException, DataAccessException {

                if (rs.next()) {
                    Long id = rs.getLong(1);
                    return id;
                }
                
                return 0L;
            }
        });
        
        return maxId + 1L;
    }

    @Override
    public void create(UsuarioEntity usuarioEntity) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        
        parameterSource.addValue("usuarioId", usuarioEntity.getId());
        parameterSource.addValue("username", usuarioEntity.getUsername());
        parameterSource.addValue("password", usuarioEntity.getPassword());
        parameterSource.addValue("apellidos", usuarioEntity.getApellidos());
        parameterSource.addValue("nombres", usuarioEntity.getNombres());
        parameterSource.addValue("email", usuarioEntity.getEmail());
        parameterSource.addValue("accountNonExpired", usuarioEntity.getAccountNonExpired());
        parameterSource.addValue("accountNonLocked", usuarioEntity.getAccountNonLocked());
        parameterSource.addValue("credentialsNonExpired", usuarioEntity.getCredentialsNonExpired());
        parameterSource.addValue("enabled", usuarioEntity.getEnabled());

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        
        template.update(SQL_INSERT, parameterSource);
    }

    @Override
    public void update(Long id, UsuarioEntity usuarioEntity) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        
        parameterSource.addValue("id", usuarioEntity.getId());
        parameterSource.addValue("username", usuarioEntity.getUsername());
        parameterSource.addValue("apellidos", usuarioEntity.getApellidos());
        parameterSource.addValue("nombres", usuarioEntity.getNombres());
        parameterSource.addValue("email", usuarioEntity.getEmail());
        parameterSource.addValue("accountNonExpired", usuarioEntity.getAccountNonExpired());
        parameterSource.addValue("accountNonLocked", usuarioEntity.getAccountNonLocked());
        parameterSource.addValue("credentialsNonExpired", usuarioEntity.getCredentialsNonExpired());
        parameterSource.addValue("enabled", usuarioEntity.getEnabled());

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        
        template.update(SQL_UPDATE, parameterSource);
    }

    @Override
    public void delete(Long id) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        template.update(SQL_DELETE, parameterSource);
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
