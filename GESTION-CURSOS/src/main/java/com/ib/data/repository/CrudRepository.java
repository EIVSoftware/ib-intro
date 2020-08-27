package com.ib.data.repository;

public interface CrudRepository<T> {

    public Iterable<T> findAll();
}
