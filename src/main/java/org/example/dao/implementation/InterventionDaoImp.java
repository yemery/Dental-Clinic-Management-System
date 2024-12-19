package org.example.dao.implementation;

import org.example.dao.IDao;
import org.example.model.Intervention;

import java.util.ArrayList;
import java.util.List;

public class InterventionDaoImp implements IDao<Intervention,Long> {
    private final List<Intervention> interventions= new ArrayList<>();
    @Override
    public List<Intervention> getAll() throws Exception {
        if (!interventions.isEmpty()) {
            return interventions;
        }
        throw new Exception("No interventions found");
    }

    @Override
    public Intervention getById(Long ID) throws Exception {
        return interventions.stream().filter(
                intervention -> ID.equals(intervention.getId())

        ).findFirst().orElseThrow( ()-> new Exception("No intervention found"));
    }

    @Override
    public Intervention add(Intervention intervention) throws Exception {
        if(intervention != null){
            interventions.add(intervention);
            return intervention;
        }
        throw new Exception("No Intervention been added");

    }

    @Override
    public Intervention update(Intervention intervention) throws Exception {
        Intervention existingIntervention = getById(intervention.getId());
        if(existingIntervention != null){
            existingIntervention.setPrice(intervention.getPrice());
        return existingIntervention;
        }
        throw new Exception("No Intervention been updated");
    }

    @Override
    public void delete(Intervention intervention) throws Exception {
            if (intervention != null){
                interventions.remove(intervention);
            }
    }
}
