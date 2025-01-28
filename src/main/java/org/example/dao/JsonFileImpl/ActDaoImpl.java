package org.example.dao.JsonFileImpl;

import org.example.model.Act;

public class ActDaoImpl extends JsonDaoImpl <Act,Long>{

    public ActDaoImpl(String fileName) {
        super(fileName, Act.class);
    }

    public Act findByName(String name) throws Exception {
        return getAll().stream()
                .filter(act -> act.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
