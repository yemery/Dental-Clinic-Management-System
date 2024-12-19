package org.example.presentation.view;

import org.example.model.Act;
import org.example.model.Intervention;
import org.example.model.enums.ActCategory;
import org.example.presentation.controller.InterventionController;

public class InterventionView {
    public static void main(String[] args) {
        InterventionController interventionC = new InterventionController();

        Act a1 = new Act("Act1",2000, ActCategory.CARE_OF_CAVITIES);
        Act a2 = new Act("Act2",2000, ActCategory.DENTAL_PROSTHESES);
        Act a3 = new Act("Act3",2000, ActCategory.IMPLANTOLOGY);
        Act a4 = new Act("Act4",2000, ActCategory.DIAGNOSIS);

        Intervention i1 = new Intervention(900);
        Intervention i2 = new Intervention(90);
        i1.setActs(a1);
        i1.setActs(a2);
        i1.setActs(a3);
        i1.setActs(a4);

        i2.setActs(a1);
        i2.setActs(a2);
        i2.setActs(a3);
        i2.setActs(a4);

        interventionC.addIntervention(i1);
        interventionC.addIntervention(i2);


        interventionC.displayAllInterventions();
        System.out.println("******************************");

        i1.setPrice(800);

        interventionC.updateIntervention(i1);
        interventionC.displayAllInterventions();





    }
}
