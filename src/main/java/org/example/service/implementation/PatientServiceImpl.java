package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.PatientDaoImpl;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Patient;
import org.example.service.api.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {
//    private final IDao<Patient,Long > dao = new PatientDaoImpl();
    private final IDao<Patient,Long > dao = new JsonDaoImpl<>("Patient.json", Patient.class);

    @Override
    public Patient addPatient(Patient Patient) {
        try{
            return  dao.add(Patient);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Patient getPatient(Long id) {
        try{
            System.out.println("This Patient is Found");
            return dao.getById(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Patient updatePatient(Patient Patient) {
        try{
            return dao.update(Patient);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deletePatient(Long ID) {
        try{
            dao.delete(ID);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getPatients() {
        try{
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
