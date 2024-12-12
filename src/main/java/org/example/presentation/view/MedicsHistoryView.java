package org.example.presentation.view;

import org.example.model.MedicalHistory;
import org.example.model.enums.CategoryMedicalHistory;
import org.example.model.enums.Risk;
import org.example.presentation.controller.MedicsHistoryController;

public class MedicsHistoryView {
    public static void main(String[] args) {
        MedicsHistoryController mhC = new MedicsHistoryController();

        MedicalHistory mh = new MedicalHistory("label 1", "desc 1", CategoryMedicalHistory.ALLERGY, Risk.MEDIUM);

        mhC.addMHistory(mh);
        mhC.addMHistory(new MedicalHistory("label 2", "desc 2", CategoryMedicalHistory.CHRONIC_DISEASE, Risk.HIGH));
        mhC.addMHistory(new MedicalHistory("label 3", "desc 3", CategoryMedicalHistory.HEREDITARY_DISEASE, Risk.MEDIUM));
        mhC.addMHistory(new MedicalHistory("label 4", "desc 4", CategoryMedicalHistory.OTHER, Risk.LOW));

        mhC.showAllMHistories();
//        mh.setRisk(Risk.LOW);
        mhC.deleteMHistory(mh);
        mhC.showAllMHistories();

    }
}
