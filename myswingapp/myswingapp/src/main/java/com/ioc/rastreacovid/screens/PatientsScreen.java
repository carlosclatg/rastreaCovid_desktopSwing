package com.ioc.rastreacovid.screens;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.*;

@Getter
@Setter

//In this class we design and apply logic to the patient window.

public class PatientsScreen {

	private JFrame frame;
	private Panel panel;
	private JTextField txtLlistatPacients;
	private JTable table;

	// Create the application.

	public PatientsScreen() {
		initialize();
	}

	// Initialize the contents of the frame.

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		frame.setTitle("Llista de pacients");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		// We will display the patients in a list with the columns corresponding to the
		// information we need
		// Define the columns
		Vector columnNames = new Vector();
		columnNames.add("ID");
		columnNames.add("Nom");
		columnNames.add("Cognom");
		columnNames.add("Tlf");
		columnNames.add("Data Naixament");
		columnNames.add("Data PCR");
		columnNames.add("Nº de Contactes");
		columnNames.add("Nº de Símptones");

		Vector fileVector = new Vector();

		// Consult the existing patients and list them with columns
		List<Patient> patients = ApiConnector.getAllPatients(token);
		System.out.println(patients.size());

		patients.forEach(p -> {
			Vector<Object> row = new Vector<Object>();
			row.add(p.get_id());
			row.add(p.getName());
			row.add(p.getSurname());
			row.add(p.getPhone());
			row.add(p.getBirthdate().toString());
			row.add(p.getPCRDate().toString());
			row.add(p.getContacts().size());
			row.add(p.getSintoms().size());
			fileVector.add(row);
		});

		// Define the table
		table = new JTable(fileVector, columnNames);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				// If you double click it shows the symptoms in the new window
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
					String token = prefs.get("token", "token");
					System.out.println(token);
					System.out.println(
							ApiConnector.getPacientById(token, (String) table.getValueAt(table.getSelectedRow(), 0)));
				}
			}
		});

		// We define the table format
		table.setDefaultEditor(Object.class, null);
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setGridColor(Color.cyan);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.cyan);
		table.setSelectionBackground(Color.blue);
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setVisible(true);

		this.getFrame().add(jScrollPane);
	}

	// Generation of getters & setters
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public Window getFrame() {
		return frame;
	}

	public void setSize(int i, int j) {
	}

	public void setVisible(boolean b) {
	}

}
