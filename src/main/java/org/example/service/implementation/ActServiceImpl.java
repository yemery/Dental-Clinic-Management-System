package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.implementation.ActImpl;
import org.example.model.Act;
import org.example.service.api.ActService;

import java.util.List;

public class ActServiceImpl implements ActService {


    private final IDao<Act,String > dao = new ActImpl();
//    private final IDao<Act,String > dao = new ActImpl();

    @Override
    public Act addAct(Act act) {
        try{
            return dao.add(act);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Act getAct(int id) {
        return null;
    }

    @Override
    public Act updateAct(Act act) {
        return null;
    }

    @Override
    public void deleteAct(int id) {

    }

    @Override
    public List<Act> getActs() {
        return List.of();
    }
}
