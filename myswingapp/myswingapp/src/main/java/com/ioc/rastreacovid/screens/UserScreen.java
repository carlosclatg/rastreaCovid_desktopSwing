package com.ioc.rastreacovid.screens;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.Patient;
import com.ioc.rastreacovid.mappers.PatientDetail;
import com.ioc.rastreacovid.mappers.User;

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

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Panel;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;

public class UserScreen {

	private JFrame frame;
	private Panel panel;
	private JTable table;

	public UserScreen() {
		initialize();
	}

	// Initialize the contents of the frame.

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		frame.setTitle("Llista d'usuaris");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		// We will display the patients in a list with the columns corresponding to the
		// information we need
		// Define the columns
		Vector columnNames = new Vector();
		columnNames.add("Nom");
		columnNames.add("Cognom");
		columnNames.add("Email");
		columnNames.add("Rol");
		columnNames.add("Telèfon");
		Vector fileVector = new Vector();

		// Consult the existing patients and list them with columns
		List<User> users = ApiConnector.getUsers(token)
;		System.out.println(users.size());

		users.forEach(u -> {
			Vector<Object> row = new Vector<Object>();
			row.add(u.getName());
			row.add(u.getSurname());
			row.add(u.getEmail());
			row.add(u.getType());
			row.add(u.getPhone());
			fileVector.add(row);
		});

		// Define the table
		table = new JTable(fileVector, columnNames);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				/*// If you double click it shows the symptoms in the new window
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
					String token = prefs.get("token", "token");
					System.out.println(token);
					PatientDetail detail = ApiConnector.getPacientById(token,
							(String) table.getValueAt(table.getSelectedRow(), 0));
					PatientDetailsScreen pds = new PatientDetailsScreen(detail);
					pds.getFrame().setVisible(true);
				}*/
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
