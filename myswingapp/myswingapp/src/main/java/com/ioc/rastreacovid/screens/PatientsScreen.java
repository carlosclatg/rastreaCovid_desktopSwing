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
public class PatientsScreen {
	
	private JFrame frame;
	private Panel panel;
	private JTextField txtLlistatPacients;
	private JButton btnEnrere;

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Dispose on close, otherwise closes all the app

		frame.setTitle("Llista de pacients");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		Vector columnNames =  new Vector();
		columnNames.add("_id");
		columnNames.add("name");
		columnNames.add("surname");
		columnNames.add("phone");
		columnNames.add("birthdate");
		columnNames.add("PCR");
		columnNames.add("number of contacts");
		columnNames.add("number of symptoms");


		Vector fileVector = new Vector();

		List<Patient> patients = ApiConnector.getAllPatients(token);



		patients.forEach(p -> {
			Vector<Object> row= new Vector<Object>();
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



		JTable jTable = new JTable(fileVector, columnNames);

		jTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				//si haces 2xclick
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
					String token = prefs.get("token", "token");
					System.out.println(token);
					System.out.println(ApiConnector.getPacientById(token, (String) table.getValueAt(table.getSelectedRow(), 0)));
				}
			}
		});
		jTable.setDefaultEditor(Object.class, null);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		jScrollPane.setVisible(true);

		this.getFrame().add(jScrollPane);
		
		btnEnrere = new JButton("Enrere");
		btnEnrere.setBounds(50, 50, 50, 50);
		frame.getContentPane().add(btnEnrere, BorderLayout.SOUTH);
	
		
	}

}
