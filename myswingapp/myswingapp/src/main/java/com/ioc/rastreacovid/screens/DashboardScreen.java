package com.ioc.rastreacovid.screens;

import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;
import javax.swing.*;

import com.ioc.rastreacovid.communication.ApiConnector;

//In this class we design and apply the logic to the dashboard of our application

public class DashboardScreen extends JFrame implements ActionListener, WindowListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton pacientsButton;
	private JButton usuarisButton;

	public DashboardScreen() {
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		// Main screen
		JPanel panel = new JPanel();
		panel.setBackground(new Color(92, 255, 208));
		panel.setLayout(null);
		panel.setBounds(100, 100, 500, 500);
		getContentPane().add(panel, BorderLayout.CENTER);
		setTitle("Dashboard");

		// Button to logout and return to the login screen
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(164, 217, 170, 70);
		logoutButton.addActionListener(this);
		panel.add(logoutButton);

		// Button to go to the patients window.
		pacientsButton = new JButton("Pacients");
		pacientsButton.setBounds(164, 76, 170, 70);
		panel.add(pacientsButton);
		pacientsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PatientScreen screen = new PatientScreen();
				screen.getFrame().setVisible(true);
			}
		});

		// Button to go to the users window, we prepare the implementation for the next
		// sprint, we hide the button
		usuarisButton = new JButton("Usuaris");
		usuarisButton.setBounds(297, 76, 170, 70);
		usuarisButton.setVisible(false);
		panel.add(usuarisButton);
		usuarisButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserScreen screen = new UserScreen();
				screen.getFrame().setVisible(true);
			}
		});

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
	// Action to return to the login window.

	@Override
	public void actionPerformed(ActionEvent e) {
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		prefs.remove("token");
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

	}

	// Generation of getters & setters
	public JButton getLogoutButton() {
		return getLogoutButton();
	}

	public void setLogoutButton(JButton logoutButton) {
	}

	public JButton getUsuarisButton() {
		return usuarisButton;
	}

	public void setUsuarisButton(JButton usuarisButton) {
		this.usuarisButton = usuarisButton;
	}

	public JButton getPacientsButton() {
		return pacientsButton;
	}

	public void setPacientsButton(JButton pacientsButton) {
		this.pacientsButton = pacientsButton;
	}

}
