package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.JsonFileImpl.PatientDaoImpl;
import org.example.model.Patient;
import org.example.service.api.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    private final PatientDaoImpl dao = new PatientDaoImpl("Patient.json");

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

    public Patient getPatient(String cin){

        return dao.getPatientByCIN()
    }


}
