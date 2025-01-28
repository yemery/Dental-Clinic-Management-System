package org.example.dao.JsonFileImpl;

import org.example.model.Medicine;

public class MedicineDaoImpl extends JsonDaoImpl<Medicine, Long>{
    public MedicineDaoImpl(String fileName) {
        super(fileName, Medicine.class);
    }
}
