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
	public DashboardScreen() {
        Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
        String token = prefs.get("token", "token");
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(92, 255, 208));
        panel.setLayout(null);
        panel.setBounds(100, 100, 500, 500);
        getContentPane().add(panel, BorderLayout.CENTER);
        setTitle("Dashboard");
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(164, 217, 170, 70);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);
        
        JButton usuarisButton = new JButton("Usuaris");
        usuarisButton.setBounds(297, 76, 170, 70);
        panel.add(usuarisButton);
        
        JButton pacientsButton = new JButton("Pacients");
        pacientsButton.setBounds(29, 76, 170, 70);
        panel.add(pacientsButton);
 

        JLabel tokenLabel = new JLabel();
        tokenLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
        tokenLabel.setBounds(100, 321, 299, 34);
        tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tokenLabel.setText(token);
        panel.add(tokenLabel);
        
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
        frame.setSize(500, 500);
        frame.setVisible(true);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        
    }
    
    public void actionPerformed1(ActionEvent e) {
    	UserScreen frame = new UserScreen();
    	frame.setSize(500, 500);
    	frame.setVisible(true);
    	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_ACTIVATED));
    	
    }
    

    public JButton getLogoutButton() {
        return getLogoutButton();
    }
    
    //TO DO
    public JButton getUsuarisButton() {
    	return getUsuarisButton();
    }
    
    //TO DO
    public JButton getpacientsButton() {
    	return getpacientsButton();
    	
    }

    public void setLogoutButton(JButton logoutButton) {
    }
    
  //TO DO
    public void setUsuarisbutton(JButton usuarisButton) {
    }
    
  //TO DO
    public void setpacientsButton(JButton pactientsButton) {
    }
    
}

