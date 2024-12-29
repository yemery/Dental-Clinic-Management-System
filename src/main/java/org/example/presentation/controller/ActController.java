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
    public void displayAllActs(){
        actService.getActs().forEach(System.out::println);
    }
    public void updateAct(Act act) {
        actService.updateAct(act);
    }
    public void deleteAct(Act act) {
        actService.deleteAct(act);
    }


}
