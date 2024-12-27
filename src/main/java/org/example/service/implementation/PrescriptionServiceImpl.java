package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.PrescriptionDaoImpl;
import org.example.model.Prescription;
import org.example.model.PrescriptionMedicine;
import org.example.service.api.PrescriptionService;

import java.util.List;

public class PrescriptionServiceImpl implements PrescriptionService {
    public final IDao<Prescription, Long> dao = new PrescriptionDaoImpl();

    @Override
    public Prescription getPrescriptionById(Long ID) {
        try{
            return dao.getById(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prescription addPrescription(Prescription prescription) {
        try{
            return dao.add(prescription);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prescription updatePrescription(Prescription prescription) {
        return null;
    }

    @Override
    public void deletePrescription(Prescription prescription) {
        try{
            dao.delete(prescription);
            System.out.println("Prescription deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        try{
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removePrescriptionMedicine(Prescription prescription,PrescriptionMedicine prescriptionMedicine) {
        try{
            prescription.removePrescriptionMedicine(prescriptionMedicine);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
