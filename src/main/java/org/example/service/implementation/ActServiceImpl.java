package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Act;
import org.example.model.Intervention;
import org.example.service.api.ActService;
import org.example.service.api.InterventionService;

import java.util.List;

public class ActServiceImpl implements ActService {
    private final IDao<Act,Long > dao = new JsonDaoImpl<>("Acts.json" , Act.class);
    private final InterventionService interventionService = new InterventionServiceImpl();

    @Override
//    public boolean isExists(Act act1) {
//        boolean updated = false;
//        List<Intervention> interventions = interventionService.getAllInterventions();
//        for (Intervention intervention : interventions) {
//            if (intervention.getActs().contains(act1)) {
//
//                // intervention.removeAct(act);
//                System.out.println("found in the intervention" + intervention.getId() + " * " +act1.getId() );
////                    System.out.println(intervention.getActs().size());
//                Act oldRecord = intervention.getActs().stream().filter(act -> act.getId().equals(act1.getId())).findFirst().get();
//
////                System.out.println(oldRecord);
////                System.out.println("new one");
////                System.out.println(act1);
//                if (interventionService.removeAct(intervention, oldRecord)){
//                    interventionService.addAct(intervention, act1);
//                    System.out.println("Removed Secc And Added Secc");
////                    System.out.println(intervention.getActs().size());
//                }else{
//                    System.out.println("Failed to remove ");
//                }
////                System.out.println(intervention);
//
//            }
//        }
//
//
//        System.out.println(interventions);
//        return updated;
//    }
//    public boolean isExists(Act act1) {
//        //    1. target intervention
////        2. update act
////        3. update intervention
//        boolean updated = false;
//
//        // Get all interventions
//        List<Intervention> interventions = interventionService.getAllInterventions();
//        // 1. search fr interventions containing act (working)
//        // 2. update act in its file (working)
//        // 3. updating the intervention acts list
//            // a. we should update the methods add and remove to the list
//        for (Intervention intervention : interventions) {
//            // Check if the intervention contains the act
//            if (intervention.getActs().contains(act1)) {
//                System.out.println("Found in the intervention " + intervention.getId() + " * " + act1.getId());
//
//                // Find the existing act in the intervention
//                Act oldRecord = intervention.getActs()
//                        .stream()
//                        .filter(act -> act.getId().equals(act1.getId()))
//                        .findFirst()
//                        .orElse(null);
//
//                if (oldRecord != null) {
//                    // Attempt to remove the old act
//                    if (interventionService.removeAct(intervention, oldRecord)) {
//                        System.out.println("Successfully removed old act.");
//
//                        // Add the new act to the intervention
////                        if (interventionService.addAct(intervention, act1)) {
////                            System.out.println("Successfully added new act.");
////                            updated = true;
////                        } else {
////                            System.out.println("Failed to add the new act.");
////                        }
//                    } else {
//                        System.out.println("Failed to remove the old act.");
//                    }
//                } else {
//                    System.out.println("No matching old act found.");
//                }
//            }
//        }
//        System.out.println(interventions);
//        return updated;
//    }

    public boolean isExists(Act act1) {
        boolean updated = false;

        // Get all interventions
        List<Intervention> interventions = interventionService.getAllInterventions();

        for (Intervention intervention : interventions) {
            // Check if the intervention contains the act
            if (intervention.getActs().contains(act1)) {
                System.out.println("Found in the intervention " + intervention.getId() + " * " + act1.getId());
                System.out.println(intervention.getActs().size());
                intervention.getActs().remove(act1);
                System.out.println(intervention.getActs().size());

                System.out.println(interventions);
                intervention.getActs().add(act1);
                System.out.println(interventions);
                System.out.println(intervention.getActs().size());

                interventionService.updateIntervention(intervention);
            }
        }
//        for (Intervention intervention : interventions) {
//            interventionService.updateIntervention(intervention);
//        }

        return updated;
    }
    @Override
    public Act addAct(Act act) {
        try{
            Act a= dao.add(act);
            System.out.println("Act Added Seccessfully");
            return a ;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Act getAct(Long id) {
        try{
            System.out.println("This Act is Found");
            return dao.getById(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Act updateAct(Act act) {
        try{
            dao.update(act);
            if (this.isExists(act)) {

                System.out.println("This Act is Found and Updated in Intervention db");
            }
            System.out.println("This Act is Found and Updated in Intervention db");

            System.out.println("Act Updated Successfully");
            // call a method inside the intervention to check where the act is and update it

            return act ;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteAct(Long ID) {
            try{
//                Act act = getById(ID);
                dao.delete(ID);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                throw new RuntimeException();
            }
    }

    @Override
    public List<Act> getActs() {
        try{
            return dao.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }



}
