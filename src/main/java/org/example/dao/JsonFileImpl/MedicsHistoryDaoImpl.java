package org.example.dao.JsonFileImpl;

import org.example.model.MedicalHistory;

public class MedicsHistoryDaoImpl extends JsonDaoImpl<MedicalHistory, Long>{
    public MedicsHistoryDaoImpl(String fileName) {
        super(fileName, MedicalHistory.class);
    }
}
