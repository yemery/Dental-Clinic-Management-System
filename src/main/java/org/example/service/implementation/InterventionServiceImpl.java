package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.InterventionDaoImp;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Act;
import org.example.model.Consultation;
import org.example.model.Intervention;
import org.example.service.api.ConsultationService;
import org.example.service.api.InterventionService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterventionServiceImpl implements InterventionService {

//    public final IDao<Intervention, Long> dao = new InterventionDaoImp();
    public final IDao<Intervention, Long> dao = new JsonDaoImpl<>("Interventions.json" , Intervention.class);


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
            this.deleteFromConsultation(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeAct(Intervention intervention, Long actId) {
        try{

            Intervention i = dao.getById(intervention.getId());

            List<Long> interventionActList=  i.getActs();
            System.out.println(interventionActList.size());
            interventionActList.remove(actId);
            System.out.println(interventionActList.size());
            i.setActs(interventionActList);

            this.updateIntervention(intervention);

            System.out.println(i);

            return true;
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean addAct(Intervention intervention, Long actId) {
        try{
            List<Long> interventionActList = intervention.getActs();
            interventionActList.add(actId);
            intervention.setActs(interventionActList);

            System.out.println(getAllInterventions().size());
            return true;
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteFromConsultation(Long ID) {
        ConsultationService consultationsService = new ConsultationServiceImpl();
        List<Consultation> consultations = consultationsService.getAllConsultations();

        AtomicBoolean updated = new AtomicBoolean(false); // used to allow boolean values modification within lambdas
        consultations.stream().filter(consultation -> consultation.getInterventions().contains(ID))
                .forEach(consultation -> {
                    consultation.getInterventions().remove(ID);

                    // not updating the file content but the update is shown using sout
                    consultationsService.updateConsultation(consultation);
                    updated.set(true);
                });

        return updated.get();
    }
}
