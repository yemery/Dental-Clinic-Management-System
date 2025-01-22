package org.example.utils;

import org.example.model.enums.UserType;

public class NavbarLinks {
    private final String[] DoctorLinks= new String[]{"Dashboard", "MedicalCases","Appointments", "Patients", "Consultations", "Acts", "Interventions","Certificates", "Invoices", "Medicines", "Prescriptions", "PrescriptionMedicines", "MedicalHistories", "Staff","Logout"};
    private final String[] AssitantLinks= new String[]{"Dashboard","Appointments" ,"Patients" ,"Consultations","Interventions","Invoices","Logout"};

    public String[] getNavlinks(UserType userType) {
        if (userType.equals(UserType.DOCTOR)){
            return DoctorLinks;
        } else if (userType.equals(UserType.ASSISTANT)) {
            return AssitantLinks;
        }
        return null;
    }
}
