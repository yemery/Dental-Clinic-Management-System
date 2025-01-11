package org.example.presentation.view.frames;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() {
        this.setTitle("Dental App");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height); // Set frame to full screen
    }
}
