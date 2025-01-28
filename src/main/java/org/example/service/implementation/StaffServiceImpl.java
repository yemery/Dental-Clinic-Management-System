package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.JsonFileImpl.StaffDaoImpl;
import org.example.model.Staff;
import org.example.model.User;
import org.example.model.enums.UserType;
import org.example.service.api.StaffService;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    private final IDao<Staff, Long> dao = new StaffDaoImpl("Staffs.json");
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

    @Override
    public Long login(String username , String password) {
        try {
            List<Staff> allUsers = this.getAllUsers();

            Staff foundStaff = allUsers.stream().filter(u -> u.getUsername().equals(username) &&
                     u.getPassword().equals(password)
                    ).findFirst().orElse(null);
            if (foundStaff != null) {
                return foundStaff.getId();
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserType getRole(Long id) {
        try {
            Staff staff = dao.getById(id);
            return staff.getUserType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
