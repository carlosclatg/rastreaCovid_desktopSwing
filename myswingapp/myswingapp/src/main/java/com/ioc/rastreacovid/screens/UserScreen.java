package com.ioc.rastreacovid.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Panel;

public class UserScreen {

	private JFrame frame;
	private Panel panel;
	private JTextField txtLlistatDusuaris;
	private JButton btnEnrere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserScreen window = new UserScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserScreen() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		txtLlistatDusuaris = new JTextField();
		txtLlistatDusuaris.setHorizontalAlignment(SwingConstants.CENTER);
		txtLlistatDusuaris.setText("Llistat d'usuaris");
		txtLlistatDusuaris.setBackground(new Color(92, 255, 208));
		panel.add(txtLlistatDusuaris);
		txtLlistatDusuaris.setColumns(10);
		
		btnEnrere = new JButton("Enrere");
		btnEnrere.setBounds(50, 50, 50, 50);
		frame.getContentPane().add(btnEnrere, BorderLayout.SOUTH);	
		
	}

	public void setSize(int i, int j) {
		// TODO Esbozo de método generado automáticamente
		
	}

	public void setVisible(boolean b) {
		// TODO Esbozo de método generado automáticamente
		
	}

	public JComponent getFrame() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

}
