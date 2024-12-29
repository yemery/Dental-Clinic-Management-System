package org.example.dao.ArrayListImpl;

import org.example.dao.IDao;
import org.example.model.Act;

import java.util.ArrayList;
import java.util.List;

public class ActDaoImpl implements IDao<Act,Long> {
    private final List<Act> acts = new ArrayList<>();

    @Override
    public List<Act> getAll() throws Exception {
        if (!acts.isEmpty()){
            return acts;
        }
        throw new Exception("No Acts Found");
    }

    @Override
    public Act getById(Long ID) throws Exception {
        return acts.stream()
                .filter(act -> ID.equals(act.getId()))
                .findFirst()
                .orElseThrow(() -> new Exception("No Act found with ID: " + ID));
    }


    @Override
    public Act add(Act act) throws Exception {
        if (act != null){
            acts.add(act);
            return act;
        }

       throw new Exception("No Act Been Added");
    }

    @Override
    public Act update(Act act) throws Exception {
        Act existingAct = this.getById(act.getId());

        existingAct.setName(act.getName());
        existingAct.setCategory(act.getCategory());
        existingAct.setName(act.getName());
        existingAct.setBasePrice(act.getBasePrice());

        return existingAct;
    }

    @Override
    public void delete(Long ID) throws Exception {
            Act existingAct = this.getById(ID);

            acts.remove(existingAct);
    }
}
