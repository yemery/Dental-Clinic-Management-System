package org.example.dao.JsonFileImpl;

import org.example.model.MedicalCase;

public class MedicalCaseDaoImpl extends JsonDaoImpl<MedicalCase,Long>{
    public MedicalCaseDaoImpl(String fileName) {
        super(fileName, MedicalCase.class);
    }
}
