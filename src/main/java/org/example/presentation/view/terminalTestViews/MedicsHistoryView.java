package org.example.presentation.view.terminalTestViews;

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

//        System.out.println(mhC.getMh(17L)   );
//
//        MedicalHistory mh = mhC.getMh(17L);
//        mh.setLabel("new upadted label");
//            mhC.updateMHistory(mh);
//            System.out.println(mhC.getMh(17L));
//
//        System.out.println(mhC.showAllMHistories().size());
//
//        mhC.deleteMHistory(16L);
//        System.out.println(mhC.showAllMHistories().size());
//        mhC.showAllMHistories();
//        mh.setRisk(Risk.LOW);
//        mhC.deleteMHistory(mh);
//        mhC.showAllMHistories();
        mhC.deleteMHistory(17L);

    }
}
