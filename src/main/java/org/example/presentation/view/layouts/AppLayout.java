package org.example.presentation.view.layouts;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Act;
import org.example.model.Appointment;
import org.example.model.Intervention;
import org.example.model.Patient;
import org.example.presentation.controller.ActController;
import org.example.presentation.controller.AppointmentController;
import org.example.presentation.controller.InterventionController;
import org.example.presentation.controller.PatientController;
import org.example.presentation.view.components.molecules.NavigationBar;
import org.example.presentation.view.frames.Acts.Acts;
import org.example.presentation.view.frames.Acts.AddAct;
import org.example.presentation.view.frames.Appoitments.AddAppointment;
import org.example.presentation.view.frames.Appoitments.Appointments;
import org.example.presentation.view.frames.Dashboard;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.frames.Interventions.AddIntervention;
import org.example.presentation.view.frames.Interventions.Interventions;
import org.example.presentation.view.frames.Patient.AddPatient;
import org.example.presentation.view.frames.Patient.Patients;
import org.example.utils.ConvertArray;
import org.example.utils.ConvertArray.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
        new AppLayout("Dashboard", "Appointments", "Patients", "Consultations", "Acts", "Interventions");
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

        Object[][] appointmentsArray = ConvertArray.convertTo2DArray(
                appointments,
                appointment -> List.of(appointment.getId(), appointment.getDate(), appointment.getTime(), appointment.getType(), appointment.getStatus())
        );
        String columns[] = {"ID", "Date", "Time", "Type", "Status", "Actions"};

        setContent(new Appointments(appointmentsArray, this, "Add new Appointment", columns,
                a -> new AddAppointment(this)
        ));
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
}