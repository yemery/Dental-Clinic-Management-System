package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.implementation.InterventionImp;
import org.example.model.Intervention;
import org.example.service.api.InterventionService;

import java.util.List;

public class InterventionServiceImpl implements InterventionService {
    public final IDao<Intervention, Long> dao = new InterventionImp();

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
    public void deleteIntervention(Intervention intervention) {
        try{
            dao.delete(intervention);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
