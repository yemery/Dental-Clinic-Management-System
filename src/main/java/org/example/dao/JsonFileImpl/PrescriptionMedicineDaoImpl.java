package org.example.dao.JsonFileImpl;

import org.example.model.PrescriptionMedicine;

public class PrescriptionMedicineDaoImpl extends JsonDaoImpl<PrescriptionMedicine, Long> {
    public PrescriptionMedicineDaoImpl(String fileName) {
        super(fileName, PrescriptionMedicine.class);
    }
}
