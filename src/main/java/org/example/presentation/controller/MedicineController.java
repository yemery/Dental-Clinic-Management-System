package org.example.presentation.controller;

import org.example.model.Medicine;
import org.example.service.api.MedicineService;
import org.example.service.implementation.MedicineServiceImp;

public class MedicineController {
    private final MedicineService medicineService = new MedicineServiceImp();

    public void add(Medicine med) {medicineService.addMedicine(med);}
    public void update(Medicine med) {medicineService.updateMedicine(med);}
    public void delete(Medicine med) {medicineService.deleteMedicine(med);}
    public void getMedicine(){medicineService.getMedicine().forEach(System.out::println);}

}
