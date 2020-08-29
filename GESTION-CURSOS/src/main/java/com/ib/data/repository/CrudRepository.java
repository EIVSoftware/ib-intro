package com.ib.data.repository;

import java.util.Optional;

public interface CrudRepository<T, I> {

    public Iterable<T> findAll();
    
    public Optional<T> findById(I id);
}
