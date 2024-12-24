package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.implementation.ConsultationDaoImpl;
import org.example.model.Certificate;
import org.example.model.Consultation;
import org.example.model.Intervention;
import org.example.service.api.ConsultationService;

import java.util.List;

public class ConsultationServiceImpl implements ConsultationService {
    private final IDao<Consultation, Long> dao = new ConsultationDaoImpl();

    @Override
    public Consultation addConsultation(Consultation consultation) {
        try{

            return dao.add(consultation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Consultation updateConsultation(Consultation consultation) {
        try{
            return dao.update(consultation);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Consultation getConsultation(Long ID) {
        try{
            return dao.getById(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Consultation> getAllConsultations() {
        try{
            return dao.getAll();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        try{
            dao.delete(consultation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeIntervention(Consultation consultation, Intervention intervention) {
        try{
            consultation.removeIntervention(intervention);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
