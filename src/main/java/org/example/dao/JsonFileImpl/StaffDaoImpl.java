package org.example.dao.JsonFileImpl;

import org.example.model.Staff;

public class StaffDaoImpl extends JsonDaoImpl<Staff, Long>{
    public StaffDaoImpl(String fileName) {
        super(fileName, Staff.class);
    }
}