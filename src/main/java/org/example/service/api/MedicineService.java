package org.example.service.api;

import org.example.model.Appointment;
import org.example.model.MedicalCase;
import org.example.model.Medicine;
import org.example.model.PrescriptionMedicine;

import java.util.List;

public interface MedicineService {
    Medicine addMedicine(Medicine medicine);
    Medicine getMedicine(Long id);
    Medicine updateMedicine(Medicine medicine);
    void deleteMedicine(Long ID);
    List<Medicine> getMedicines();

    public boolean removeMedicineFromPrescriptionMedicine(Long ID);
}
