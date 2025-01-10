package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.MedicineDaoIml;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Consultation;
import org.example.model.Medicine;
import org.example.model.Prescription;
import org.example.model.PrescriptionMedicine;
import org.example.service.api.MedicineService;
import org.example.service.api.PrescriptionMedicineService;
import org.example.service.api.PrescriptionService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MedicineServiceImp implements MedicineService {

//    private  final IDao<Medicine,Long> dao = new MedicineDaoIml();
    private  final IDao<Medicine,Long> dao = new JsonDaoImpl<>("Medicines.json", Medicine.class);

    @Override
    public Medicine addMedicine(Medicine medicine) {
        try{
            System.out.println("This medicine is added");
            return dao.add(
                    medicine
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Medicine getMedicine(Long id) {
        try{
            System.out.println("This Medicne is Founded");
            return dao.getById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Medicine updateMedicine(Medicine medicine) {
        try{
            dao.update(medicine);
            System.out.println("This Medicne is Updated");
            return medicine;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMedicine(Long ID) {
    try{
        System.out.println("This Medicine is deleted");
        dao.delete(ID);
        this.removeMedicineFromPrescriptionMedicine(ID);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        throw new RuntimeException(e);
    }
    }

    @Override
    public List<Medicine> getMedicines() {
        try{
            return dao.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeMedicineFromPrescriptionMedicine( Long ID) {
        PrescriptionMedicineService prescriptionMedicineService= new PrescriptionMedicineServiceImpl();
        List<PrescriptionMedicine> prescriptionMedicineList = prescriptionMedicineService.getAllMedicinePrescription();

        AtomicBoolean updated = new AtomicBoolean(false);
        prescriptionMedicineList.stream().filter(pm -> pm.getMedicine().equals(ID))
                .forEach(pm -> {

                   pm.setMedicine(0L);
                    prescriptionMedicineService.updateMedicinePrescription(pm);
                    updated.set(true);
                });

        return updated.get();

    }

}
