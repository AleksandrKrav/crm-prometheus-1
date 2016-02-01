package com.becomejavasenior;

import java.util.List;

public interface GenericService<T, D> {
    void add(T t) throws ServiceException;
    void delete(T t) throws ServiceException;
    void update(T t) throws ServiceException;
    T getById(Integer id) throws ServiceException;
    List<T> getAll() throws ServiceException;
}
