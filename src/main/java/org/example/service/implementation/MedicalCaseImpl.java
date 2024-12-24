package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.implementation.MedicalCaseDaoImpl;
import org.example.model.*;
import org.example.model.MedicalCase;
import org.example.service.api.MedicalCaseService;

import java.util.List;

public class MedicalCaseImpl implements MedicalCaseService {
    private final IDao<MedicalCase, Long> dao = new MedicalCaseDaoImpl();

    @Override
    public MedicalCase addMedicalCase(MedicalCase MedicalCase) {
        try {
            return dao.add(MedicalCase);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MedicalCase getMedicalCase(Long id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MedicalCase updateMedicalCase(MedicalCase MedicalCase) {
        try {
            return dao.update(MedicalCase);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMedicalCase(MedicalCase medicalCase) {
        try {
            dao.delete(medicalCase);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<MedicalCase> getAllMedicalCases() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeAppointment(MedicalCase medicalCase, Appointment appointment) {
        try {
            medicalCase.removeAppointment(appointment);
            // what abt invoking delete method to remove the object from
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean removeMedicalHistory(MedicalCase medicalCase, MedicalHistory medicalHistory) {
        try {
            return medicalCase.removeMedicalHistory(medicalHistory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
