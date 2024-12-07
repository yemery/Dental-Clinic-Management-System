package org.example.dao.implementation;

import org.example.dao.IDao;
import org.example.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineDaoIml implements IDao<Medicine,Long> {
    private final List<Medicine> medicines= new ArrayList<Medicine>();
    @Override
    public List<Medicine> getAll() throws Exception {
        if(!medicines.isEmpty()){
            return medicines;
        }
        throw new Exception("No medicines found");
    }

    @Override
    public Medicine getById(Long ID) throws Exception {
        return medicines.stream()
                .filter(medicine -> ID.equals(medicine.getId()))
                .findFirst()
                .orElseThrow(()-> new Exception("No Medicine found with ID: " + ID));
    }

    @Override
    public Medicine add(Medicine medicine) throws Exception {
        return null;
    }

    @Override
    public Medicine update(Medicine medicine) throws Exception {
        return null;
    }

    @Override
    public void delete(Medicine medicine) throws Exception {

    }
}
