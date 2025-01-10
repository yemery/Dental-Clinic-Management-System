package org.example.presentation.view;

import org.example.model.Medicine;
import org.example.presentation.controller.MedicineController;

public class MedicineView {
    public static void main(String[] args) {
        MedicineController medC = new MedicineController();

//        medC.add(new Medicine(82.5, "med 1", "desc 1"));
//        medC.add(new Medicine(30.0, "med 2", "desc 2"));
//        medC.add(new Medicine(45.5, "med 3", "desc 3"));

//        Medicine m4 = new Medicine(40.0, "med 4", "desc 4");
//        medC.add(m4);
        Medicine m1= medC.getMedicine(82L);
        System.out.println(m1);
        m1.setName("updated Medicine");
        medC.updateMedicine(m1);
//        System.out.println(medC.getAllMedicine().size());
//        medC.deleteMedicine(64L);
        System.out.println(medC.getAllMedicine().size());

//        System.out.println(medC.getMedicine(64L));
//        medC.getAllMedicine();
//        m4.setPrice(50.0);
//        medC.update(m4);
//        medC.delete(m4);

//        medC.getAllMedicine();
    }
}
