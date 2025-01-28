package org.example;

import org.example.presentation.view.frames.Auth;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtilities.invokeLater(Auth::new);
    }
}