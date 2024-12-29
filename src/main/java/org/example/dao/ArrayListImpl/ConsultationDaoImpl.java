package org.example.dao.ArrayListImpl;

import org.example.dao.IDao;
import org.example.model.Act;
import org.example.model.Consultation;

import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImpl implements IDao<Consultation,Long> {
    private final List<Consultation> consultations= new ArrayList<>();
    @Override
    public List<Consultation> getAll() throws Exception {
        if(!consultations.isEmpty()){
            return consultations;
        }
        throw new Exception("No intervention been founded");

    }

    @Override
    public Consultation getById(Long ID) throws Exception {
        return consultations.stream()
                .filter(consultation -> ID.equals(consultation.getId()))
                .findFirst()
                .orElseThrow(() -> new Exception("No consultation found"));
    }


    @Override
    public Consultation add(Consultation consultation) throws Exception {
        if (consultation != null){
            consultations.add(consultation);
            return  consultation;
        }
        throw  new Exception("No Consultation been added");
    }

    @Override
    public Consultation update(Consultation consultation) throws Exception {
        Consultation existingConsultation= getById(consultation.getId());
        if (existingConsultation != null){
            existingConsultation.setType(consultation.getType());
            existingConsultation.setNote(consultation.getNote());
            existingConsultation.setCertificate(consultation.getCertificate());
            existingConsultation.setDate(consultation.getDate());
            existingConsultation.setPrescription(consultation.getPrescription());
            existingConsultation.setInvoice(consultation.getInvoice());
            return existingConsultation;
        }
        throw  new Exception("No Consultation been updated");
    }

    @Override
    public void delete(Long ID) throws Exception {
        Consultation existingConsultation = this.getById(ID);

        consultations.remove(existingConsultation);
    }
}
