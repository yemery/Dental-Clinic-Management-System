package org.example.presentation.view.frames.Appoitments;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Table;

import javax.swing.*;
import java.awt.*;

public class Appointments extends JPanel {
    private JPanel container;
    private JPanel addContainer;

    Button addAppointmentBtn;
    Table appointmentsTable;

    public Appointments() {
        super();
        this.setLayout(new BorderLayout());
        this.container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        this.addAppointmentBtn = new Button("Add Appointment");
//        this.addAppointmentBtn.addActionListener(); // action to have a new frame to add appointment

        this.add(this.addAppointmentBtn);
        this.addContainer = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                Dimension dim = super.getPreferredSize();
                dim.width = getParent().getWidth();
                return dim;
            }
        };

        this.addContainer.add(this.addAppointmentBtn, BorderLayout.WEST);
        this.container.add(this.addContainer);

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] cols = {"patient", "date", "time", "purpose", "status", "action"};
        this.appointmentsTable = new Table(null, cols);
        tableContainer.add(new JScrollPane(appointmentsTable), BorderLayout.CENTER);
        this.container.add(tableContainer);

        this.add(this.container, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
