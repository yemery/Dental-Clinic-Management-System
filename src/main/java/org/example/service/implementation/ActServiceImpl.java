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
    public boolean isExists(Act act1) {
        boolean updated = false;

        List<Intervention> interventions = interventionService.getAllInterventions();

        for (Intervention intervention : interventions) {
            // Check if the intervention contains the act
            if (intervention.getActs().contains(act1)) {
                updated = true;
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
    public boolean deleteActInIntervention(Act act1) {
        boolean updated = false;

        List<Intervention> interventions = interventionService.getAllInterventions();

        for (Intervention intervention : interventions) {
            // Check if the intervention contains the act
            if (intervention.getActs().contains(act1)) {
                updated = true;
                System.out.println("Found in the intervention " + intervention.getId() + " * " + act1.getId());
                System.out.println(intervention.getActs().size());
                intervention.getActs().remove(act1);
                System.out.println(intervention.getActs().size());

                System.out.println(interventions);
//                intervention.getActs().add(act1);
                System.out.println(interventions);
                System.out.println(intervention.getActs().size());

                interventionService.updateIntervention(intervention);
            }
        }

        return updated;
    }
    @Override
    public void deleteAct(Long ID) {
            try{
//                Act act = getById(ID);
                System.out.println("hhhhh");
                Act act = dao.getById(ID);
//                System.out.println(act);
                dao.delete(ID);

                if (this.deleteActInIntervention(act)) {
                    System.out.println("Act Deleted Successfully");

                }else {
                    System.out.println("Act Not Deleted Successfully");
                }
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
