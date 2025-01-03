package org.example.dao.ArrayListImpl;

import org.example.dao.IDao;
import org.example.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements IDao<Patient,Long> {
    private final List<Patient> patients = new ArrayList<Patient>();

    @Override
    public List<Patient> getAll() throws Exception {
        if (!patients.isEmpty()) {
            return patients;
        }
        throw new Exception("No patients found");
    }

    @Override
    public Patient getById(Long ID) throws Exception {
        return patients.stream().filter(patient -> patient.getId().equals(ID)).findFirst().orElseThrow(
                () -> new Exception("No patient found with ID " + ID)
        );

    }

    @Override
    public Patient add(Patient patient) throws Exception {
        if (patient != null) {
            patients.add(patient);
            return patient;

        }
        throw new Exception("Duplicate patient found");
    }

    @Override
    public Patient update(Patient patient) throws Exception {
        Patient existingPatient = getById(patient.getId());
        existingPatient.setBirthDate(patient.getBirthDate());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setGender(patient.getGender());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setPhone(patient.getPhone());
        existingPatient.setMutuelle(patient.getMutuelle());
        existingPatient.setJob(patient.getJob());
        existingPatient.setRegistration(patient.getRegistration());
        existingPatient.setCIN(patient.getCIN());
        existingPatient.setFirstName(patient.getFirstName());
//        existingPatient.setMedicalCase(patient.getMedicalCase());
        return existingPatient;


    }

    @Override
    public void delete(Long ID) throws Exception {
        Patient existingPatient = this.getById(ID);
        patients.remove(existingPatient);
    }
}
