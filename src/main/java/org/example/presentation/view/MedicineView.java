package org.example.presentation.view;

import org.example.model.Medicine;
import org.example.presentation.controller.MedicineController;

public class MedicineView {
    public static void main(String[] args) {
        MedicineController medC = new MedicineController();

        medC.add(new Medicine(82.5, "med 1", "desc 1"));
        medC.add(new Medicine(30.0, "med 2", "desc 2"));
        medC.add(new Medicine(45.5, "med 3", "desc 3"));

        Medicine m4 = new Medicine(40.0, "med 4", "desc 4");
        medC.add(m4);

        medC.getMedicine();
        m4.setPrice(50.0);
//        medC.update(m4);
        medC.delete(m4);

        medC.getMedicine();
    }
}
