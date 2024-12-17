package org.example.service.api;

import org.example.model.Medicine;

import java.util.List;

public interface MedicineService {
    Medicine addMedicine(Medicine medicine);
    Medicine getMedicine(Long id);
    Medicine updateMedicine(Medicine medicine);
    void deleteMedicine(Medicine medicine);
    List<Medicine> getMedicine();
}
