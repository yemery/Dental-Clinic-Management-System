package org.example.presentation.view;

import org.example.model.Act;
import org.example.model.Intervention;
import org.example.model.enums.ActCategory;
import org.example.presentation.controller.ActController;
import org.example.presentation.controller.InterventionController;

import java.util.List;

public class InterventionView {
    public static void main(String[] args) {
        InterventionController interventionC = new InterventionController();
//        ActController actC = new ActController();
//        Act act1 = actC.getAct(1L);
//
//
//        Act a1 = new Act("Act1",2000, ActCategory.CARE_OF_CAVITIES);
//        Act a2 = new Act("Act2",2000, ActCategory.DENTAL_PROSTHESES);
//        Act a3 = new Act("Act3",2000, ActCategory.IMPLANTOLOGY);
//        Act a4 = new Act("Act4",2000, ActCategory.DIAGNOSIS);
//
//        actC.addNewAct(a1);
//        actC.addNewAct(a2);
//        actC.addNewAct(a3);
//        actC.addNewAct(a4);
//
//        Intervention i1 = new Intervention(900);
//        Intervention i2 = new Intervention(90);
//        List<Act> actList1 = List.of(
//               a1,a2
//        );
//        List<Act> actList2 = List.of(
//               a3,a4
//        );
//        i1.setActs(actList1);
//        i2.setActs(actList2);

//        interventionC.addIntervention(i1);
//        interventionC.addIntervention(i2);
//        interventionC.deleteIntervention(i1.getId());


//        interventionC.displayAllInterventions();
//        System.out.println("******************************");
//
        Intervention i1 = interventionC.getIntervention(1L);
        i1.setPrice(800);
        interventionC.updateIntervention(i1);

//        System.out.println(i1.getActs().size());
//        interventionC.removeAct(i1,a1);
//        System.out.println(i1.getActs().size());
//        System.out.println(a1);
//        interventionC.displayAllInterventions();
//        interventionC.deleteIntervention(10L);

    }
}
