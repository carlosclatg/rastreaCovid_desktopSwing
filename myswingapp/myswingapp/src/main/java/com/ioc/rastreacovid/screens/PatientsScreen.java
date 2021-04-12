package com.ioc.rastreacovid.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PatientsScreen {
	
	private JFrame frame;
	private Panel panel;
	private JTextField txtLlistatPacients;
	private JButton btnEnrere;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientsScreen window = new PatientsScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PatientsScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		txtLlistatPacients = new JTextField();
		txtLlistatPacients.setHorizontalAlignment(SwingConstants.CENTER);
		txtLlistatPacients.setText("Llistat de Pacients");
		txtLlistatPacients.setBackground(new Color(92, 255, 208));
		panel.add(txtLlistatPacients);
		txtLlistatPacients.setColumns(10);
		
		btnEnrere = new JButton("Enrere");
		btnEnrere.setBounds(50, 50, 50, 50);
		frame.getContentPane().add(btnEnrere, BorderLayout.SOUTH);
	
		
	}

}
