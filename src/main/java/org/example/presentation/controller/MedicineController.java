package org.example.presentation.controller;

import org.example.model.Medicine;
import org.example.service.api.MedicineService;
import org.example.service.implementation.MedicineServiceImp;

import java.util.List;

public class MedicineController {
    private final MedicineService medicineService = new MedicineServiceImp();

    public void addMedicine(Medicine med) {medicineService.addMedicine(med);}
    public void updateMedicine(Medicine med) {medicineService.updateMedicine(med);}
    public void deleteMedicine(Long ID) {medicineService.deleteMedicine(ID);}

    public List<Medicine> getAllMedicine() {
        return medicineService.getMedicines();
    }

    public Medicine getMedicine(Long ID) {
        return medicineService.getMedicine(ID);
    }
}
