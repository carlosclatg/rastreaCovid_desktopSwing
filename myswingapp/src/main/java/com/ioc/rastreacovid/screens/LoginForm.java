package com.ioc.rastreacovid.screens;

import com.ioc.rastreacovid.communication.ApiConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.prefs.Preferences;

public class LoginForm extends JFrame implements ActionListener, WindowListener {
    JButton SUBMIT;
    JPanel panel;
    JLabel label1, label2;
    final JTextField text1, text2;

    public LoginForm() {
        label1 = new JLabel();
        label1.setText("Username:");
        text1 = new JTextField(15);

        label2 = new JLabel();
        label2.setText("Password:");
        text2 = new JPasswordField(15);

        SUBMIT = new JButton("SUBMIT");

        panel = new JPanel(new GridLayout(3, 1));
        panel.add(label1);
        panel.add(text1);
        panel.add(label2);
        panel.add(text2);
        panel.add(SUBMIT);
        add(panel, BorderLayout.CENTER);
        SUBMIT.addActionListener(this);
        setTitle("LOGIN FORM");
    }


    public void actionPerformed(ActionEvent ae) {
        String value1 = text1.getText();
        String value2 = text2.getText();

        String token = ApiConnector.authenticate(value1, value2);

        if (token!=null) {
            Preferences prefs = Preferences.userNodeForPackage(getClass());
            prefs.put("token", "token");
            JOptionPane.showMessageDialog(this, token,
                    "Success in login", JOptionPane.INFORMATION_MESSAGE);


        } else {
            System.out.println("enter the valid username and password");
            JOptionPane.showMessageDialog(this, "Incorrect login or password, or server error",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
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
}