package com.ioc.rastreacovid.screens;

import com.ioc.rastreacovid.communication.ApiConnector;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.prefs.Preferences;

public class LoginForm extends JFrame implements ActionListener, WindowListener {
    JButton button;
    JPanel panel;
    JLabel label1, label2;
    final JTextField text1, text2;
    String token;

    public LoginForm() {
    	getContentPane().setBackground(new Color(92, 255, 208));
    	setBackground(new Color(92, 255, 208));
    	setMaximumSize(new Dimension (500, 500));
        text1 = new JTextField(15);
        text1.setHorizontalAlignment(SwingConstants.CENTER);

        label2 = new JLabel();
        label2.setBackground(new Color(135, 206, 235));
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        
        label2.setText("Password:");
        text2 = new JPasswordField(15);
        text2.setHorizontalAlignment(SwingConstants.CENTER);

        button = new JButton("Accedir");
        button.setBackground(new Color(255, 255, 255));
       
        

        panel = new JPanel();
        panel.setBackground(new Color(92, 255, 208));
        label1 = new JLabel();
        label1.setBackground(new Color(92, 255, 208));
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setText("Username:");
        getContentPane().add(panel, BorderLayout.NORTH);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
        						.addComponent(label2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(label1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        						.addComponent(text1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
        						.addComponent(text2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(32)
        					.addComponent(button, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(32, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(21)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(label1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
        				.addComponent(text1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(label2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
        				.addComponent(text2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(button, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(65, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        button.addActionListener(this);
        setTitle("Rastreacovid");
    }


    public void actionPerformed(ActionEvent ae) {
        String value1 = text1.getText();
        String value2 = text2.getText();

        String token = ApiConnector.authenticate(value1, value2);

        if (token != null) {
            Preferences prefs = Preferences.userNodeForPackage(getClass());
            prefs.put("token", token);
            DashboardScreen frame = new DashboardScreen();
           frame.setSize(500, 500);
           frame.setVisible(true);
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        } else {
            System.out.println("enter the valid username and password");
            JOptionPane.showMessageDialog(this, "Incorrect login or password, or server error", "Error",
                    JOptionPane.ERROR_MESSAGE);
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


    public JButton getSUBMIT() {
        return button;
    }


    public void setSUBMIT(JButton sUBMIT) {
        button = button;
    }


    public JPanel getPanel() {
        return panel;
    }


    public void setPanel(JPanel panel) {
        this.panel = panel;
    }


    public JTextField getText1() {
        return text1;
    }


    public JTextField getText2() {
        return text2;
    }


    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }
}