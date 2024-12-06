package org.example.dao;

import java.util.List;

public interface IDao<T, ID>  {
    List<T> getAll() throws Exception;
    T getById(ID id) throws Exception;
    T add(T t) throws Exception;
    T update(T t) throws Exception;
    void delete(T t) throws Exception;
}
