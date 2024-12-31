package org.example.service.api;

import org.example.model.Act;

import java.util.List;

public interface ActService {
    Act addAct(Act act);
    Act getAct(Long id);
    Act updateAct(Act act);
    void deleteAct(Long id);
    List<Act> getActs();
    boolean isExists(Act act);
    boolean deleteActInIntervention(Act act);


}
