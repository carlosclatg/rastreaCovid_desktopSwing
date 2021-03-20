package com.ioc.rastreacovid.screens;

import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;
import javax.swing.*;

public class DashboardScreen extends JFrame implements ActionListener, WindowListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JButton logoutButton;
    JPanel panel;
    JLabel tokenLabel;

    public DashboardScreen() {
        Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
        String token = prefs.get("token", "token");
        tokenLabel = new JLabel();
        tokenLabel.setText(token);
        logoutButton = new JButton("Logout");
        logoutButton.setSize(80, 20);
        logoutButton.setText("Logout");

        panel = new JPanel(new GridLayout(3, 1));
        panel.add(tokenLabel);
        panel.add(logoutButton);
        add(panel, BorderLayout.CENTER);
        logoutButton.addActionListener(this);
        setTitle("LOGOUT");
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
        prefs.remove("token");
        LoginForm frame = new LoginForm();
        frame.setSize(400, 200);
        frame.setVisible(true);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

    }
}