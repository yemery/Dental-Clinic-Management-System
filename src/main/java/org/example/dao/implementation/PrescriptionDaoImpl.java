package org.example.dao.implementation;

import org.example.dao.IDao;
import org.example.model.Act;
import org.example.model.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionDaoImpl  implements IDao<Prescription,Long> {
    private final List<Prescription> prescriptions= new ArrayList<>();

    @Override
    public List<Prescription> getAll() throws Exception {
        if(!prescriptions.isEmpty()){
            return prescriptions;
        }
        throw new Exception("No prescriptions found");
    }

    @Override
    public Prescription getById(Long id) throws Exception {
        return prescriptions.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(()->new Exception("No prescription found"));
    }

    @Override
    public Prescription add(Prescription prescription) throws Exception {
        // condition of should not be already in the array checck by getbyid method and instanceof
        if(prescription != null){
           prescriptions.add(prescription);
           return prescription;
        }
        throw new Exception("No prescription found");
    }

    @Override
    public Prescription update(Prescription prescription) throws Exception {
       return null;
    }

    @Override
    public void delete(Prescription prescription) throws Exception {
            if (prescription != null){
                prescriptions.remove(prescription);
            }
            throw new Exception("No prescription found");

    }
}
