package com.ib.data.repository;

import org.springframework.stereotype.Repository;

import com.ib.data.entity.UsuarioEntity;

@Repository
public class UsuarioRepository implements CrudRepository<UsuarioEntity> {

    @Override
    public Iterable<UsuarioEntity> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
