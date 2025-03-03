package org.example.presentation.controller;

import org.example.model.Act;
import org.example.service.api.ActService;
import org.example.service.implementation.ActServiceImpl;

import java.util.List;

public class ActController  {
    private final ActService actService =new ActServiceImpl();


    public void addNewAct(Act act) {
        actService.addAct(act);

    }
    public List<Act> displayAllActs(){

        return actService.getActs();
    }
    public void updateAct(Act act) {
        actService.updateAct(act);
    }
    public void deleteAct(Long ID) {
        actService.deleteAct(ID);
    }

    public Act getAct(Long ID) {return actService.getAct(ID);}
    public Act findByName(String name) {
        return actService.findByName(name);
    }

}
