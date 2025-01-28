package org.example.dao.JsonFileImpl;

import org.example.model.Consultation;

public class ConsultationDaoImpl extends JsonDaoImpl<Consultation,Long>{
    public ConsultationDaoImpl(String fileName) {
        super(fileName, Consultation.class);
    }
}
