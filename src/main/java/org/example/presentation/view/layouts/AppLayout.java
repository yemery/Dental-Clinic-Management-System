package org.example.presentation.view.layouts;

import org.example.model.*;
import org.example.presentation.controller.*;
import org.example.presentation.view.components.molecules.NavigationBar;
import org.example.presentation.view.frames.Acts.Acts;
import org.example.presentation.view.frames.Acts.AddAct;
import org.example.presentation.view.frames.Appoitments.AddAppointment;
import org.example.presentation.view.frames.Appoitments.Appointments;
import org.example.presentation.view.frames.Certificates.AddCertificate;
import org.example.presentation.view.frames.Certificates.Certificates;
import org.example.presentation.view.frames.Dashboard;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.frames.Interventions.AddIntervention;
import org.example.presentation.view.frames.Interventions.Interventions;
import org.example.presentation.view.frames.Invoice.AddInvoice;
import org.example.presentation.view.frames.Invoice.Invoices;
import org.example.presentation.view.frames.Patient.AddPatient;
import org.example.presentation.view.frames.Patient.Patients;
import org.example.utils.ConvertArray;

import javax.swing.*;
import java.awt.*;
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

    public static void main(String[] args) {
        new AppLayout("Dashboard", "Appointments", "Patients", "Consultations", "Acts", "Interventions", "Certificates", "Invoices");
    }

    private void dashboardNavigation() {
        // get appointments (+user full name) and pass it to the dashboard
        Object[][] data = {
                {"John Doe", "30", "New York", "r"},
                {"Jane Smith", "25", "Los Angeles", "r"},
                {"Bob Johnson", "35", "Chicago", "r"}
        };

        setContent(new Dashboard(data));
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
        JPanel consultationsPanel = new JPanel();
        consultationsPanel.add(new JLabel("Consultations"));
        setContent(consultationsPanel);
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
                cer -> List.of(cer.getId(),cer.getReason(),cer.getStartDate(),cer.getEndDate())
        );
        String columns[] = {"ID", "Reasan" ,"Start Date","End Date","Actions"};

        setContent(new Certificates(certificatesArray, this, "Add new Certificate", columns,
                a -> new AddCertificate(this)
        ));
    }
    private void invoicesNavigation() {
        InvoiceController  invoiceController = new InvoiceController();
        List<Invoice> invoices = invoiceController.displayAllInvoices();


        Object[][] invoicesArray = ConvertArray.convertTo2DArray(
                invoices,
                inv -> List.of(inv.getId(),inv.getDate(),inv.getPayedAmount(),inv.getTotalAmount(),inv.getType())
        );
        String columns[] = {"ID", "Date","Total Amount","Payed Amount" , "Type" , "Actions"};

        setContent(new Invoices(invoicesArray, this, "Add new Invoice", columns,
                a -> new AddInvoice(this)
        ));
    }
}