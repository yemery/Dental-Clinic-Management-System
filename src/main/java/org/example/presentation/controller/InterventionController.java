package org.example.presentation.controller;

import org.example.model.Act;
import org.example.model.Intervention;
import org.example.service.api.InterventionService;
import org.example.service.implementation.InterventionServiceImpl;

public class InterventionController  {
private final InterventionService interventionService = new InterventionServiceImpl();
public void displayAllInterventions(){
    interventionService.getAllInterventions().forEach(System.out::println);
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



public boolean removeAct(Intervention intervention, Act act){
    return interventionService.removeAct(intervention, act);
}

}


