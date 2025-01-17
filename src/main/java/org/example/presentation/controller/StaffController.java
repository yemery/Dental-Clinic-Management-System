package org.example.presentation.controller;

import org.example.model.Staff;
import org.example.service.api.StaffService;
import org.example.service.implementation.StaffServiceImpl;

import java.util.List;

public class StaffController {
    public final StaffService userService = new StaffServiceImpl();

    public void addUser(Staff user) {
        userService.saveUser(user);
    }
    public List<Staff> displayUsers() {
        return userService.getAllUsers();
    }
    public Staff getUser(Long id) {
        return userService.getUser(id);
    }
    public void updateUser(Staff user) {
        userService.updateUser(user);
    }
    public void deleteUser(Long ID) {
        userService.deleteUser(ID);
    }
}
