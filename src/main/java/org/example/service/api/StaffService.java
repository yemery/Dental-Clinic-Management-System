package org.example.service.api;

import org.example.model.Staff;
import org.example.model.User;
import org.example.model.enums.UserType;

import java.util.List;

public interface StaffService {
    Staff getUser(Long id);
    void saveUser(Staff user);
    void deleteUser(Long id);
    void updateUser(Staff user);
    List<Staff> getAllUsers();
    Long login(String username, String password);
    UserType getRole(Long id);
}
