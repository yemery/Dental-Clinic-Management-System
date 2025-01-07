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

            System.out.println("Act Updated Successfully");

            return act ;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteActInIntervention(Long actId) {
        boolean updated = false;

        List<Intervention> interventions = interventionService.getAllInterventions();

        for (Intervention intervention : interventions) {
            // Check if the intervention contains the act
            if (intervention.getActs().contains(actId)) {
                updated = true;
                System.out.println("Found in the intervention " + intervention.getId() + " * " + actId);
                System.out.println(intervention.getActs().size());
                intervention.getActs().remove(actId);
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
                Act act = dao.getById(ID);
                dao.delete(ID);

                if (this.deleteActInIntervention(ID)) {
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
