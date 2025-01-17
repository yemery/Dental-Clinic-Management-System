package org.example.service.api;

import org.example.model.Staff;

import java.util.List;

public interface StaffService {
    Staff getUser(Long id);
    void saveUser(Staff user);
    void deleteUser(Long id);
    void updateUser(Staff user);
    List<Staff> getAllUsers();
}
