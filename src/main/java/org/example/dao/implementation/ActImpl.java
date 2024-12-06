package org.example.dao.implementation;

import org.example.dao.IDao;
import org.example.model.Act;

import java.util.ArrayList;
import java.util.List;

public class ActImpl implements IDao<Act,String> {
    private List<Act> acts = new ArrayList<Act>();

    @Override
    public List<Act> getAll() throws Exception {
        if (!acts.isEmpty()){
            return acts;
        }
        throw new Exception("No Acts Found");
    }

    @Override
    public Act getById(String ID) throws Exception {
        return acts.stream()
                .filter(act -> ID.equals(act.getId()))
                .findFirst()
                .orElseThrow(() -> new Exception("No Act found with ID: " + ID));
    }


    @Override
    public Act add(Act act) throws Exception {
        if (!act.equals(null)){
            acts.add(act);
            return act;
        }

       throw new Exception("No Act Been Added");
    }

    @Override
    public Act update(Act act) throws Exception {
        Act existingAct = acts.stream()
                .filter(a -> a.getId().equals(act.getId()))
                .findFirst()
                .orElseThrow(() -> new Exception("No Act found with ID: " + act.getId()));

        existingAct.setName(act.getName());

        return existingAct;
    }

    @Override
    public void delete(Act act) throws Exception {

    }
}
