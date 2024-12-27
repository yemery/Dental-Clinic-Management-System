package org.example.dao.JsonFileImpl;

import org.example.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class JsonDaoImpl<T,ID> implements IDao<T,ID> {
//    private final String filePath;
    private final List<T> data = new ArrayList<>();
//    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<T> getAll() throws Exception {
        return List.of();
    }

    @Override
    public T getById(ID id) throws Exception {
        return null;
    }

    @Override
    public T add(T t) throws Exception {
        return null;
    }

    @Override
    public T update(T t) throws Exception {
        return null;
    }

    @Override
    public void delete(T t) throws Exception {

    }
}
