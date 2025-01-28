package org.example.dao.JsonFileImpl;

import org.example.model.Intervention;

public class InterventionDaoImpl extends JsonDaoImpl<Intervention,Long>{
    public InterventionDaoImpl(String fileName) {
        super(fileName, Intervention.class);
    }
}
