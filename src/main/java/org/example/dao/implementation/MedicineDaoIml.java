package org.example.dao.implementation;

import org.example.dao.IDao;
import org.example.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineDaoIml implements IDao<Medicine, Long> {
    private final List<Medicine> medicines = new ArrayList<Medicine>();

    @Override
    public List<Medicine> getAll() throws Exception {
        if (!medicines.isEmpty()) {
            return medicines;
        }
        throw new Exception("No medicines found");
    }

    @Override
    public Medicine getById(Long ID) throws Exception {
        return medicines.stream()
                .filter(medicine -> ID.equals(medicine.getId()))
                .findFirst()
                .orElseThrow(() -> new Exception("No Medicine found with ID: " + ID));
    }

    @Override
    public Medicine add(Medicine medicine) throws Exception {
        if (medicine.equals(null)) throw new Exception("Medicine is null");
        medicines.add(medicine);
        return medicine;
    }

    @Override
    public Medicine update(Medicine medicine) throws Exception {
        Medicine rec = this.getById(medicine.getId());

        rec.setName(medicine.getName());
        rec.setPrice(medicine.getPrice());
        rec.setDescription(medicine.getDescription());

        return medicine;
    }

    @Override
    public void delete(Medicine medicine) throws Exception {
        if (medicines.contains(medicine)) medicines.remove(medicine);
        else throw new Exception("Medicine not found");
    }
}
