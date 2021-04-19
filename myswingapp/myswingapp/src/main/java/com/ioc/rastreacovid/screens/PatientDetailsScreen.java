package com.ioc.rastreacovid.screens;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.Patient;
import com.ioc.rastreacovid.mappers.PatientDetail;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;

//In this class we design and apply logic to the patient details window.

public class PatientDetailsScreen {

	private JFrame frmDetailPacient;
	private JTextField textField;
	private JLabel lblNewLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientDetailsScreen window = new PatientDetailsScreen(null);
					window.frmDetailPacient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.

	public PatientDetailsScreen(PatientDetail patientdetail) {
		initialize(patientdetail);
	}

	// Initialize the contents of the frame.
	/**
	 * 
	 * @param patientdetail
	 */
	public void initialize(PatientDetail patientdetail) {
		frmDetailPacient = new JFrame();
		frmDetailPacient.getContentPane().setBackground(new Color(92, 255, 208));
		frmDetailPacient.setBounds(100, 100, 1000, 500);
		frmDetailPacient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all
																			// the app

		frmDetailPacient.setTitle("Detall del Pacient");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		frmDetailPacient.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// We show the "Nom" label and the patient's name
		JLabel name = new JLabel("Nom:");
		name.setBounds(10, 10, 100, 30);
		name.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		JTextField namep = new JTextField();
		namep.setBounds(120, 10, 150, 20);
		namep.setText(patientdetail.getName());
		namep.setEditable(false);
		namep.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		namep.setBackground(new Color(92, 255, 208));

		// We show the "Cognom" label and the patient's surname.
		JLabel surname = new JLabel("Cognom:");
		surname.setBounds(10, 10, 100, 30);
		surname.setFont(new Font("Lucida Grande", Font.BOLD, 15));

		JTextField surnamep = new JTextField();
		surnamep.setBounds(120, 10, 150, 20);
		surnamep.setText(patientdetail.getSurname());
		surnamep.setEditable(false);
		surnamep.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		surnamep.setBackground(new Color(92, 255, 208));

		// Add the frame
		this.getFrame().add(name);
		this.getFrame().add(namep);
		this.getFrame().add(surname);
		this.getFrame().add(surnamep);

		// We look at all the symptoms that the patient has and show them with a while
		int nsintoms = patientdetail.getSintoms().size();
		int counter = 0;

		while (counter < nsintoms) {
			JLabel sintom = new JLabel("Símptoma" + " " + (counter + 1) + ":");
			sintom.setBounds(10, 10, 100, 30);
			sintom.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			JTextField sintomp = new JTextField();
			sintomp.setBounds(120, 10, 150, 20);
			sintomp.setText(patientdetail.getSintoms().get(counter).getSintoma_cat());
			sintomp.setEditable(false);
			sintomp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			sintomp.setBackground(new Color(92, 255, 208));

			counter++;

			this.getFrame().add(sintom);
			this.getFrame().add(sintomp);
		}

		// We look at all the contacts that the patient has and show them with a while.
		int ncontacts = patientdetail.getContacts().size();
		int counter2 = 0;
		
		while (counter2 < ncontacts) {
			JLabel contact = new JLabel("Contacte" + " " + (counter2 + 1) + ":");
			contact.setBounds(10, 10, 100, 30);
			contact.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			contact.setVisible(true);
			JTextField contactp = new JTextField();
			contactp.setBounds(120, 10, 150, 20);
			contactp.setText(patientdetail.getContacts().get(counter2).getName());
			contactp.setEditable(false);
			contactp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			contactp.setBackground(new Color(92, 255, 208));
			contactp.setVisible(true);

			counter2++;

			this.getFrame().add(contact);
			this.getFrame().add(contactp);
		}
	}

	public Window getFrame() {
		return frmDetailPacient;
	}

	public void setSize(int i, int j) {
	}

	public void setVisible(boolean b) {
	}
}
