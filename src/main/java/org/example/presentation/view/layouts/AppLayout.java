package org.example.presentation.view.layouts;

import org.example.model.*;
import org.example.model.enums.AppointementStatus;
import org.example.presentation.controller.*;
import org.example.presentation.records.AppointmentPatientInfo;
import org.example.presentation.view.components.molecules.NavigationBar;
import org.example.presentation.view.frames.Acts.Acts;
import org.example.presentation.view.frames.Acts.AddAct;
import org.example.presentation.view.frames.Appoitments.AddAppointment;
import org.example.presentation.view.frames.Appoitments.Appointments;
import org.example.presentation.view.frames.Auth;
import org.example.presentation.view.frames.Certificates.AddCertificate;
import org.example.presentation.view.frames.Certificates.Certificates;
import org.example.presentation.view.frames.Consultations.AddConsultation;
import org.example.presentation.view.frames.Consultations.Consultations;
import org.example.presentation.view.frames.Dashboard;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.frames.Interventions.AddIntervention;
import org.example.presentation.view.frames.Interventions.Interventions;
import org.example.presentation.view.frames.Invoice.AddInvoice;
import org.example.presentation.view.frames.Invoice.Invoices;
import org.example.presentation.view.frames.MedicalCases.AddMedicalCase;
import org.example.presentation.view.frames.MedicalCases.MedicalCases;
import org.example.presentation.view.frames.MedicalHistory.AddMedicalHistory;
import org.example.presentation.view.frames.MedicalHistory.MedicalHistories;
import org.example.presentation.view.frames.Medicines.AddMedicine;
import org.example.presentation.view.frames.Medicines.Medicines;
import org.example.presentation.view.frames.Patient.AddPatient;
import org.example.presentation.view.frames.Patient.Patients;
import org.example.presentation.view.frames.Prescriptions.AddPrescription;
import org.example.presentation.view.frames.Prescriptions.Prescriptions;
import org.example.presentation.view.frames.PrescriptionsMedicines.AddPM;
import org.example.presentation.view.frames.PrescriptionsMedicines.PrescriptionMedicines;
import org.example.presentation.view.frames.Staff.AddStaff;
import org.example.utils.ConvertArray;
import org.example.presentation.view.frames.Staff.Staffs;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;

public class AppLayout extends Frame {
    private NavigationBar navbar;
    private JPanel contentPanel; // content to be shown (pages)
    private Object[][] data;

    public AppLayout(String... tabs) {
        navbar = new NavigationBar(tabs);
        contentPanel = new JPanel();

        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        add(navbar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setupNavigation();
        navbar.simulateTabClick("Dashboard");

        setVisible(true);
    }

    private void setupNavigation() {
        // routing
        navbar.addTabListener("Dashboard", e -> {
            dashboardNavigation();
        });
        navbar.addTabListener("Patients", e -> {
            patientsNavigation();
        });
        navbar.addTabListener("Appointments", e -> {
            appointmentsNavigation();
        });
        navbar.addTabListener("Interventions", e -> {
            interventionsNavigation();
        });
        navbar.addTabListener("Acts", e -> {
            actsNavigation();
        });
        navbar.addTabListener("Consultations", e -> {
            consultationsNavigation();
        });
        navbar.addTabListener("Certificates", e -> {
            certificatesNavigation();
        });
        navbar.addTabListener("Invoices", e -> {
            invoicesNavigation();
        });
        navbar.addTabListener("Medicines", e -> {
            medicinesNavigation();
        });
        navbar.addTabListener("Prescriptions", e -> {
            prescriptionsNavigation();
        });
        navbar.addTabListener("PrescriptionMedicines", e -> {
            prescriptionMedicinesNavigation();
        });
        navbar.addTabListener("MedicalHistories", e -> {
            medicalHistoriesNavigation();
        });
        navbar.addTabListener("MedicalCases", e -> {
            medicalCasesNavigation();
        });
        navbar.addTabListener("Staff", e -> {
            staffNavigation();
        });
        navbar.addTabListener("Logout", e -> {
            logoutNavigation();
        });
    }

    private void setContent(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public NavigationBar getNavbar() {
        return navbar;
    }

    private void dashboardNavigation() {
        InvoiceController invoiceC = new InvoiceController();
        AppointmentController appointmentC = new AppointmentController();
        MedicalCaseController medicalCaseC = new MedicalCaseController();
        PatientController patientC = new PatientController();

        // month income calculation - leave as is
        double income = invoiceC.displayAllInvoices()
                .stream()
                .filter(invoice -> {
                    LocalDate invoiceDate = invoice.getDate();
                    YearMonth currentMonth = YearMonth.now();
                    return YearMonth.from(invoiceDate).equals(currentMonth);
                })
                .mapToDouble(Invoice::getPayedAmount)
                .sum();

        // unpaid invoices count
        long unpaidInvoices = invoiceC.displayAllInvoices()
                .stream()
                .filter(invoice -> invoice.getPayedAmount().equals(0.0))
                .count();

        // get today s appointments
        List<AppointmentPatientInfo> todaysAppointments = medicalCaseC.getAllMedicalCase().stream()
                .flatMap(medicalCase -> medicalCase.getAppointments().stream()
                        .map(appointmentId -> {
                            try {
                                Appointment appointment = appointmentC.getAppointment(appointmentId);
                                Patient patient = patientC.getPatient(medicalCase.getPatient());

                                if (appointment == null || patient == null) {
                                    return null;
                                }

                                return new AppointmentPatientInfo(
                                        appointment.getId(),
                                        appointment.getTime(),
                                        appointment.getDate(),
                                        patient.getFirstName() + " " + patient.getLastName(),
                                        appointment.getType(),
                                        appointment.getStatus()
                                );
                            } catch (Exception e) {
                                System.out.println("Error processing appointment: " + appointmentId);
                                return null;
                            }
                        }))
                .filter(appointmentInfo -> appointmentInfo != null &&
                        appointmentInfo.date().equals(LocalDate.now()) &&
                        !appointmentInfo.status().equals(AppointementStatus.CANCELLED))
                .sorted(Comparator.comparing(AppointmentPatientInfo::time))
                .toList();

        String columns[] = {"ID", "Patient", "Hour", "Type", "Status"};
        Object[][] appointmentsArray = ConvertArray.convertTo2DArray(
                todaysAppointments,
                data -> List.of(
                        data.appointmentId(),
                        data.patientFullName(),
                        data.time(),
                        data.type(),
                        data.status()
                )
        );

        setContent(new Dashboard(income, unpaidInvoices, appointmentsArray, columns));
    }

    private void patientsNavigation() {
        PatientController patientController = new PatientController();
        List<Patient> patients = patientController.displayPatients();
        Object[][] patientsArray = ConvertArray.convertTo2DArray(
                patients,
                patient -> List.of(patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getPhone(), patient.getCIN()
                )
        );
        String columns[] = {"ID", "First Name", "Last Name", "Phone Number", "Cin", "Actions"};
        setContent(new Patients(
                patientsArray,
                this,
                "Add new Patient",
                columns,
                a -> new AddPatient(this)
        ));
    }

    private void consultationsNavigation() {

        ConsultationController consultationController = new ConsultationController();
        List<Consultation> consultations = consultationController.displayAllConsultations();
        Object[][] consultationsArray = ConvertArray.convertTo2DArray(
                consultations,
                c -> List.of(c.getId(), c.getDate(), c.getNote(), c.getType()
                )
        );
        String columns[] = {"ID", "Date", "Note", "Type", "Actions"};
        setContent(new Consultations(
                consultationsArray,
                this,
                "Add new Consultation",
                columns,
                a -> new AddConsultation(this)
        ));
    }

    private void appointmentsNavigation() {
        AppointmentController controller = new AppointmentController();
        List<Appointment> appointments = controller.displayAppointments();

        // Convert appointments to a 2D array
        Object[][] appointmentsArray = ConvertArray.convertTo2DArray(
                appointments,
                appointment -> List.of(
                        appointment.getId(),
                        appointment.getDate(),
                        appointment.getTime(),
                        appointment.getType(),
                        appointment.getStatus(),
                        appointment.getConsultation() == null || appointment.getConsultation() == 0 ? "No Consultation found" : appointment.getConsultation().toString()
                )
        );

        // Define columns
        String[] columns = {"ID", "Date", "Time", "Type", "Status", "Appointment ID", "Actions"};

        // Set content with the appointments table
        setContent(
                new Appointments(appointmentsArray, this, "Add new Appointment", columns,
                        a -> new AddAppointment(this)
                )
        );
    }

    private void actsNavigation() {
        ActController actController = new ActController();
        List<Act> acts = actController.displayAllActs();


        Object[][] actsArray = ConvertArray.convertTo2DArray(
                acts,
                act -> List.of(act.getId(), act.getName(), act.getBasePrice(), act.getCategory())
        );
        String columns[] = {"ID", "Name", "Base price", "Category", "Actions"};

        setContent(new Acts(actsArray, this, "Add new Act", columns,
                a -> new AddAct(this)
        ));
    }

    private void interventionsNavigation() {
        InterventionController interventionC = new InterventionController();
        List<Intervention> interventions = interventionC.displayAllInterventions();
        Object[][] interventionsArray = ConvertArray.convertTo2DArray(
                interventions,
                intervention -> List.of(intervention.getId(), intervention.getPrice(), intervention.getActs().size()

                )
        );

        String columns[] = {"ID", "Price", "Number of Acts", "Actions"};
        setContent(new Interventions(
                interventionsArray,
                this,
                "Add new Intervention",
                columns,
                a -> new AddIntervention(this)
        ));
    }

    private void certificatesNavigation() {
        CertificateController certificateController = new CertificateController();
        List<Certificate> certificatesList = certificateController.displayAllCertificates();


        Object[][] certificatesArray = ConvertArray.convertTo2DArray(
                certificatesList,
                cer -> List.of(cer.getId(), cer.getReason(), cer.getStartDate(), cer.getEndDate())
        );
        String columns[] = {"ID", "Reason", "Start Date", "End Date", "Actions"};

        setContent(new Certificates(certificatesArray, this, "Add new Certificate", columns,
                a -> new AddCertificate(this)
        ));
    }

    private void invoicesNavigation() {
        InvoiceController invoiceController = new InvoiceController();
        List<Invoice> invoices = invoiceController.displayAllInvoices();


        Object[][] invoicesArray = ConvertArray.convertTo2DArray(
                invoices,
                inv -> List.of(inv.getId(), inv.getDate(), inv.getPayedAmount(), inv.getTotalAmount(), inv.getType())
        );
        String columns[] = {"ID", "Date", "Total Amount", "Payed Amount", "Type", "Actions"};

        setContent(new Invoices(invoicesArray, this, "Add new Invoice", columns,
                a -> new AddInvoice(this)
        ));
    }

    private void medicinesNavigation() {
        MedicineController medicineController = new MedicineController();
        List<Medicine> medicines = medicineController.getAllMedicine();


        Object[][] medicinesArray = ConvertArray.convertTo2DArray(
                medicines,
                m -> List.of(m.getId(), m.getName(), m.getDescription(), m.getPrice())
        );
        String columns[] = {"ID", "Name", "Description", "Price", "Actions"};
        setContent(new Medicines(medicinesArray, this, "Add new Medicine", columns,
                a -> new AddMedicine(this)
        ));
    }

    private void prescriptionsNavigation() {
        PrescriptionController prescriptionController = new PrescriptionController();
        List<Prescription> prescriptions = prescriptionController.displayPrescriptions();


        Object[][] prescriptionsArray = ConvertArray.convertTo2DArray(
                prescriptions,
                p -> List.of(p.getId(), p.getDate(), p.getPrescriptionsMedicine().size())
        );
        String columns[] = {"ID", "Date", "Medicine Prescriptions", "Actions"};
        setContent(new Prescriptions(prescriptionsArray, this, "Add new Prescription", columns,
                a -> new AddPrescription(this)
        ));
    }

    private void prescriptionMedicinesNavigation() {
        PrescriptionMedicineController prescriptionMedicineController = new PrescriptionMedicineController();
        List<PrescriptionMedicine> prescriptions = prescriptionMedicineController.displayAllPrescriptionMedicine();

        Object[][] prescriptionsArray = ConvertArray.convertTo2DArray(
                prescriptions,
                p ->
                        List.of(
                                p.getId(),
                                p.getDescription(),
                                p.getMin(),
                                p.getMax(),

                                p.getMedicine().equals(0L) ? "No Medicine" : p.getMedicine()
                        )
        );

        String[] columns = {"ID", "Description", "Min", "Max", "Medicine ID", "Actions"};
        setContent(new PrescriptionMedicines(
                prescriptionsArray,
                this,
                "Add new Prescription Medicine",
                columns,
                a -> new AddPM(this)
        ));
    }


    private void medicalHistoriesNavigation() {
        MedicsHistoryController controller = new MedicsHistoryController();
        List<MedicalHistory> medicalHistories = controller.showAllMHistories();

        Object[][] medicalHistoriesArray = ConvertArray.convertTo2DArray(
                medicalHistories,
                mh -> List.of(mh.getId(), mh.getLabel(), mh.getCategory(), mh.getDescription(), mh.getRisk())
        );
        String columns[] = {"ID", "Label", "Category", "Description", "Risk", "Actions"};

        setContent(new MedicalHistories(medicalHistoriesArray, this, "Add new Medical History", columns,
                a -> new AddMedicalHistory(this)
        ));
    }

    private void medicalCasesNavigation() {
        MedicalCaseController controller = new MedicalCaseController();
        List<MedicalCase> medicalCases = controller.getAllMedicalCase();

        Object[][] medicalCasesArray = ConvertArray.convertTo2DArray(
                medicalCases,
                m -> List.of(m.getId(), m.getPatient(), m.getCreationDate(), m.getAppointments().size(), m.getMedicalHistories().size())
        );
        String columns[] = {"ID", "Patient ID", "Creation Date", "Appointments Count", "Medics Histories Count", "Actions"};

        setContent(new MedicalCases(medicalCasesArray, this, "Add new Medical Case", columns,
                a -> new AddMedicalCase(this)
        ));
    }

    private void staffNavigation() {
        StaffController controller = new StaffController();
        List<Staff> staffList = controller.displayUsers();

        Object[][] staffArray = ConvertArray.convertTo2DArray(
                staffList,
                m -> List.of(m.getId(), m.getFirstName(), m.getLastName(), m.getCIN(), m.getSalary(), m.getUserType())
        );
        String columns[] = {"ID", "Fist Name", "Last Name", "CIN", "Salary", "Staff Type", "Actions"};


        setContent(new Staffs(staffArray, this, "Add new Staff", columns,
                a -> new AddStaff(this)
        ));
    }


    private void logoutNavigation(){
        this.dispose();
        new Auth();
    }
}
