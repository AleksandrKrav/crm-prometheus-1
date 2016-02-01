package com.becomejavasenior;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    /** Inserts object to database and returns 'id' */
    public Integer persist(T object)  throws PersistException;

    /** Returns object from database with primary key 'key' or 'null' */
    public T getById(Integer key) throws PersistException;

    /** Updates object to database */
    public void update(T object) throws PersistException;

    /** Deletes object from database with primary key 'key' or 'null' */
    public void delete(T object) throws PersistException;

    /** Returns list of objects from database */
    public List<T> getAll() throws PersistException;

}