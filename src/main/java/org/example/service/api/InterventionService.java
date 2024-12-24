package org.example.service.api;

import org.example.model.Act;
import org.example.model.Intervention;

import java.util.List;

public interface InterventionService {
    Intervention addIntervention(Intervention intervention);
    List<Intervention> getAllInterventions();
    Intervention getIntervention(Long ID);
    Intervention updateIntervention(Intervention intervention);
    void deleteIntervention(Intervention intervention);
    boolean removeAct(Intervention intervention, Act act);
}
