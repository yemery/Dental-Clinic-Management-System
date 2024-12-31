
package org.example.presentation.view;

import org.example.model.Act;
import org.example.model.enums.ActCategory;
import org.example.presentation.controller.ActController;

public class ActView {
    public static void main(String[] args) {
        ActController actC = new ActController();
         /*Act a1 = new Act("Act1",2000, ActCategory.CARE_OF_CAVITIES);
        Act a2 = new Act("Act2",2000, ActCategory.DENTAL_PROSTHESES);
        Act a3 = new Act("Act3",2000, ActCategory.IMPLANTOLOGY);
        Act a4 = new Act("Act4",2000, ActCategory.DIAGNOSIS);
        actC.addNewAct(a1);
        actC.addNewAct(a2);
        actC.addNewAct(a3);
        actC.addNewAct(a4);*/
        // actC.displayAllActs();
//        actC.deleteAct(123333L);


        Act act = actC.getAct(1735L);
        act.setBasePrice(5666);
        actC.updateAct(act);
        System.out.println(actC.getAct(1735L));
//        actC.displayAllActs();



    }
}
