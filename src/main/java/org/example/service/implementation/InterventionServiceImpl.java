package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.InterventionDaoImp;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Act;
import org.example.model.Intervention;
import org.example.service.api.InterventionService;

import java.util.List;
public class InterventionServiceImpl implements InterventionService {

//    public final IDao<Intervention, Long> dao = new InterventionDaoImp();
    public final IDao<Intervention, Long> dao = new JsonDaoImpl<>("Intervention.json" , Intervention.class);


    @Override
    public Intervention addIntervention(Intervention intervention) {
        try{
            return dao.add(intervention);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Intervention> getAllInterventions() {
        try{
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Intervention getIntervention(Long ID) {
        try{
            return dao.getById(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Intervention updateIntervention(Intervention intervention) {
        try{
            return dao.update(intervention);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteIntervention(Long ID) {
        try{
            dao.delete(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeAct(Intervention intervention, Act act) {
        try{
            intervention.removeAct(act);
            return true;
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean addAct(Intervention intervention, Act act) {
        try{
            intervention.setActs(act);
            return true;
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
