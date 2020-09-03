package com.ib.data.repository;

import java.util.Optional;

public interface CrudRepository<T, I> {

    public Iterable<T> findAll();
    
    public Optional<T> findById(I id);
    
    public void create(T t);

    public void update(I i, T t);
    
    public void delete(I i);
    
    public I nextId();
    
}
