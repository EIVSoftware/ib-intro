package com.ib.data.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ib.data.entity.UsuarioEntity;

@Service
public class UsuarioService {

    public List<UsuarioEntity> findAll() {
        return Arrays.asList(
                new UsuarioEntity(1L, "usuario.prueba", "123", "PEREZ", "JUAN", "juan@perez.com"),
                new UsuarioEntity(2L, "usuario.dos", "abc", "OTRO", "JUAN", "otro@perez.com"));
    }
}
