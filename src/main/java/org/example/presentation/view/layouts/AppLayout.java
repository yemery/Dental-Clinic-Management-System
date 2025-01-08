package org.example.presentation.view.layouts;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Act;
import org.example.model.Intervention;
import org.example.presentation.controller.ActController;
import org.example.presentation.controller.InterventionController;
import org.example.presentation.view.components.molecules.NavigationBar;
import org.example.presentation.view.frames.Acts.Acts;
import org.example.presentation.view.frames.Acts.AddAct;
import org.example.presentation.view.frames.Appoitments.Appointments;
import org.example.presentation.view.frames.Dashboard;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.frames.Interventions.Interventions;
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
        // kinda routing logic
        navbar.addTabListener("Dashboard", e -> {
            // get appointments (+user full name) and pass it to the dashboard
            Object[][] data = {
                    {"John Doe", "30", "New York", "r"},
                    {"Jane Smith", "25", "Los Angeles", "r"},
                    {"Bob Johnson", "35", "Chicago", "r"}
            };

            setContent(new Dashboard(data));
        });

        navbar.addTabListener("Patients", e -> {
            JPanel patientsPanel = new JPanel();
            patientsPanel.add(new JLabel("Patients"));
            setContent(patientsPanel);
        });

        navbar.addTabListener("Appointments", e -> {
            setContent(new Appointments());
        });
        navbar.addTabListener("Interventions", e -> {
            InterventionController interventionC = new InterventionController();
            List<Intervention> interventions = interventionC.displayAllInterventions();
            Object[][] interventionsArray = ConvertArray.convertTo2DArray(
                    interventions,
                    intervention -> List.of(intervention.getId(),intervention.getPrice(), intervention.getActs().size()

//                            ,intervention.getActs().stream()
//                            .map(Object::toString)
//                            .collect(Collectors.joining("; "))
                    )
            );
//            System.out.println(interventionsArray);

            String columns[] ={"ID", "Price", "Number of Acts", "Actions"};
            setContent(new Interventions(
                    interventionsArray,
                    this,
                    "Add new Intervention",
                    columns,
                    a -> System.out.println("add intervention")
            ));
        });
        navbar.addTabListener("Acts", e -> {
            ActController actController = new ActController();
            List<Act> acts = actController.displayAllActs();
//
//            List<List<? extends Serializable>> result = acts.stream()
//                    .map(act -> List.of(act.getId(), act.getName(), act.getBasePrice(), act.getCategory()))
//                    .collect(Collectors.toList());
//
//            Object[][] array = result.stream()
//                    .map(l -> l.toArray(new Object[0]))
//                    .toArray(Object[][]::new);

            Object[][] actsArray = ConvertArray.convertTo2DArray(
                    acts,
                    act -> List.of(act.getId(),act.getName(),act.getBasePrice(),act.getCategory())
            );
            String columns[] ={"ID","Name", "Base price", "Category", "Actions"};

            setContent(new Acts(actsArray, this, "Add new Act", columns ,
                    a -> new AddAct(this)
                    ));
        });

        navbar.addTabListener("Consultations", e -> {
            JPanel consultationsPanel = new JPanel();
            consultationsPanel.add(new JLabel("Consultations"));
            setContent(consultationsPanel);
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
        new AppLayout("Dashboard", "Appointments", "Patients", "Consultations", "Acts","Interventions");
    }
}