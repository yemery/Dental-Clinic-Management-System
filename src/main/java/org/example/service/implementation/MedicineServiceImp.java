package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.implementation.MedicineDaoIml;
import org.example.model.Medicine;
import org.example.service.api.MedicineService;

import java.util.List;

public class MedicineServiceImp implements MedicineService {

    private  final IDao<Medicine,Long> dao = new MedicineDaoIml();

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
    public void deleteMedicine(Medicine medicine) {
    try{
        System.out.println("This Medicine is deleted");
        dao.delete(medicine);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        throw new RuntimeException(e);
    }
    }

    @Override
    public List<Medicine> getMedicine() {
        try{
            return dao.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
