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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    
    @Configuration
    @ComponentScan("com.ib.data.repository")
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
    }
}
