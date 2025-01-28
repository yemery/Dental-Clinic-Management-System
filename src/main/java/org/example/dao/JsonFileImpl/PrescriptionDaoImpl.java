package org.example.dao.JsonFileImpl;

import org.example.model.Prescription;

public class PrescriptionDaoImpl extends JsonDaoImpl<Prescription, Long> {
    public PrescriptionDaoImpl(String fileName) {
        super(fileName, Prescription.class);
    }
}
