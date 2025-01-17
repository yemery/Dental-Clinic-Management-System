package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Staff;
import org.example.model.User;
import org.example.service.api.StaffService;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    private final IDao<Staff,Long > dao = new JsonDaoImpl<>("Staffs.json" , Staff.class);

    @Override
    public Staff getUser(Long id) {
        try{
            System.out.println("User Found");
            return dao.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(Staff user) {
        try{
            User u = dao.add(user);
            System.out.println("User Added");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteUser(Long id) {
            try{
//                User u = dao.getById(id);
                dao.delete(id);
                System.out.println("User Deleted");
            }catch(Exception e){
                throw new RuntimeException(e);
            }
    }

    @Override
    public void updateUser(Staff user) {
        try{
            dao.update(user);
            System.out.println("User Updated");
        } catch (Exception e) {
                throw new RuntimeException(e);
        }
    }

    @Override
    public List<Staff> getAllUsers() {
        try{
            return dao.getAll();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
