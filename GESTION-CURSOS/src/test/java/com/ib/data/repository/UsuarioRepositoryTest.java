package com.ib.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ib.data.entity.UsuarioEntity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UsuarioRepositoryTest.DataSourceCfg.class)
public class UsuarioRepositoryTest {

    @Autowired UsuarioRepository usuarioRepository;
    
    @Test
    public void whenFindAll_thenRetrive2Usuarios() {
        List<UsuarioEntity> usuarioEntities = (List<UsuarioEntity>) usuarioRepository.findAll();
        assertThat(usuarioEntities).hasSize(2);
    }

    @Test
    public void whenFindById_givenIdEq1_thenOk() {
        Optional<UsuarioEntity> optional = usuarioRepository.findById(1L);
        assertThat(optional).isNotEmpty();
    }

    @Test
    public void whenFindById_givenIdEq0_thenOptionalEmpty() {
        Optional<UsuarioEntity> optional = usuarioRepository.findById(0L);
        assertThat(optional).isEmpty();
    }

    @Test
    public void whenFindByUsername_givenUserWithId1_thenOk() {
        Optional<UsuarioEntity> optional = usuarioRepository.findByUsername("juan.perez");
        assertThat(optional).isNotEmpty();
        
        UsuarioEntity usuarioEntity = optional.get();
        assertThat(usuarioEntity.getId()).isEqualTo(1L);
    }

    @Test
    public void whenFindByUsername_givenAny_thenOptionalEmpty() {
        Optional<UsuarioEntity> optional = usuarioRepository.findByUsername("--");
        assertThat(optional).isEmpty();
    }

    @Test
    @Transactional
    public void whenCreate_givenUsuarioEntity_thenUsuarioOk() {

        final long id = 10L;
        final String username = "username";
        final String password = "password";
        final String apellidos = "apellidos";
        final String nombres = "nombre";
        final String email = "email";
        final Boolean accountNonExpired = true;
        final Boolean accountNonLocked = true;
        final Boolean credentialsNonExpired = true;
        final Boolean enabled = true;
        
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
        
        usuarioRepository.create(usuarioEntity);
        
        Optional<UsuarioEntity> result = usuarioRepository.findById(id);
        assertThat(result).isNotEmpty();
        
        UsuarioEntity expected = result.get();
        assertThat(expected).isEqualTo(usuarioEntity);
    }

    @Test
    @Transactional
    public void whenUpdate_givenUsuarioEntity_thenUsernameUpdated() {

        final long id = 1L;
        
        Optional<UsuarioEntity> preOptional = usuarioRepository.findById(id);
        assertThat(preOptional).isNotEmpty();
        
        UsuarioEntity pre = preOptional.get();
        pre.setUsername("--updated--");
        
        usuarioRepository.update(id, pre);
        
        Optional<UsuarioEntity> result = usuarioRepository.findById(id);
        assertThat(result).isNotEmpty();
        
        UsuarioEntity expected = result.get();
        assertThat(expected.getUsername()).isEqualTo(pre.getUsername());
        
    }

    @Test
    @Transactional
    public void whenDelete_givenUsuarioEntity_thenUsuarioOptionalEmpty() {

        final long id = 1L;
        
        Optional<UsuarioEntity> preOptional = usuarioRepository.findById(id);
        assertThat(preOptional).isNotEmpty();
        
        usuarioRepository.delete(id);
        
        Optional<UsuarioEntity> result = usuarioRepository.findById(id);
        assertThat(result).isEmpty();
    }
    
    @Configuration
    @ComponentScan("com.ib.data.repository")
    @EnableTransactionManagement
    public static class DataSourceCfg {

        @Bean
        public DataSource getDataSource() {

            DataSource ds = new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("classpath:schema.sql")
                    .addScript("classpath:data.sql")
                    .build();
            
            return ds;
        }

        @Bean
        public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }
}
