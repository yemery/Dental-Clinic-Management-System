package org.example.presentation.controller;

import org.example.model.Intervention;
import org.example.service.api.InterventionService;
import org.example.service.implementation.InterventionServiceImpl;

import java.util.List;

public class InterventionController  {
private final InterventionService interventionService = new InterventionServiceImpl();
public List<Intervention> displayAllInterventions(){
    return interventionService.getAllInterventions();
}

public void addIntervention(Intervention intervention){
    interventionService.addIntervention(intervention);
}
    public void updateIntervention(Intervention intervention){
        interventionService.updateIntervention(intervention);

    }

public void deleteIntervention(Long ID){
    interventionService.deleteIntervention(ID);
}

    public boolean removeAct(Intervention intervention, Long ID){
        return interventionService.removeAct(intervention, ID);
    }

public Intervention getIntervention(Long ID){
    return interventionService.getIntervention(ID);
}

}


