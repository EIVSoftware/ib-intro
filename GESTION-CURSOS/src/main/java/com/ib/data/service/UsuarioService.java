package com.ib.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ib.data.entity.UsuarioEntity;
import com.ib.data.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired private UsuarioRepository usuarioRepository;
    
    public List<UsuarioEntity> findAll() {
        return (List<UsuarioEntity>) usuarioRepository.findAll();
    }
}
