package com.ioc.rastreacovid.screens;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.Patient;
import com.ioc.rastreacovid.mappers.PatientDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.*;
import javax.swing.text.JTextComponent;


//In this class we design and apply logic to the patient window.

public class PatientsScreen {

	private JFrame frame;
	private Panel panel;
	private JTextField txtLlistatPacients;
	private JTable table;
	private JTextField searchName;
	private JLabel lblname;
	private JTextField searchSurname;
	
	private JLabel lblSurname;
	private JTextField searchPhone;
	private JLabel lblPhone;
	private JButton searchButton;
	private JLabel doubleClick;
	private String cadenaN;
	private String cadenaS;
	private String cadenaP;
	private Vector fileVector;
	private List<Patient> patients;

	// Create the application.

	public PatientsScreen() {
		initialize();
	}

	public PatientsScreen(Vector fileVector, String cadenaN, String cadenaS, String cadenaP) {
		this.fileVector = fileVector;
		this.cadenaN = cadenaN;
		this.cadenaS = cadenaS;
		this.cadenaP = cadenaP;

		initialize();
	}

	// Initialize the contents of the frame.

	public void initialize() {
		GridBagConstraints c = new GridBagConstraints();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app
		frame.setTitle("Llista de pacients");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");
		
		searchName = new JTextField(cadenaN != null ? cadenaN : "");
		searchSurname = new JTextField(cadenaS != null ? cadenaS : "");
		searchPhone = new JTextField(cadenaP != null ? cadenaP : "");
		lblname = new JLabel("Buscar per nom:");
		lblSurname = new JLabel("Buscar per cognom:");
		lblPhone = new JLabel("Buscar per telèfon:");
		doubleClick = new JLabel ("Fes doble click per veure el detall del pacient");
		doubleClick.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		frame.setLayout(new GridBagLayout());
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		frame.getContentPane().add(lblname, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		frame.getContentPane().add(searchName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		frame.getContentPane().add(lblSurname, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		frame.getContentPane().add(searchSurname, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		frame.getContentPane().add(lblPhone, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		frame.getContentPane().add(searchPhone, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		frame.getContentPane().add(doubleClick, c);
		

		searchName.setColumns(10);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		searchButton = new JButton("Buscar");
		
		patients = new ArrayList();
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileVector.clear();
				boolean filtraNom = false;
				boolean filtraCognom = false;
				boolean filtraTlf = false;
				CharSequence csn = searchName.getText(); // String is already a CharSequence
				CharSequence css = searchSurname.getText();
				CharSequence csp = searchPhone.getText();
				
				if (csn.length() > 0)
					filtraNom = true;
				if (css.length() > 0)
					filtraCognom = true;
				if (csp.length() > 0)
					filtraTlf = true;
				if (filtraNom || filtraCognom || filtraTlf)
					fileVector = filtrar(filtraNom, filtraCognom, filtraTlf, csn, css, csp);
				PatientsScreen screen = new PatientsScreen(fileVector, searchName.getText(), searchSurname.getText(),
						searchPhone.getText());
				screen.getFrame().setVisible(true);
				frame.dispose();

			}
		});
		
		frame.setTitle("Llista de Pacients");

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
		
		if (fileVector == null || (fileVector != null && fileVector.size() == 0)) {
			fileVector = new Vector();
			patients = ApiConnector.getAllPatients(token);
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
		}
		
		// Define the table
		table = new JTable(fileVector, columnNames);
		table.setAutoCreateRowSorter(true);
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
					PatientDetail detail = ApiConnector.getPacientById(token, (String) table.getValueAt(table.getSelectedRow(), 0));
					PatientDetailsScreen pds = new PatientDetailsScreen(detail);
					pds.getFrame().setVisible(true);
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
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 300; // make this component tall
		c.weightx = 0.0;
		c.gridwidth = 30;
		c.gridx = 0;
		c.gridy = 4;
		frame.getContentPane().add(jScrollPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		frame.getContentPane().add(searchButton, c);
	}

	private Vector filtrar(boolean filtraNom, boolean filtraCognom, boolean filtraTlf, CharSequence csn,
			CharSequence css, CharSequence csp) {
		Vector fileVector = new Vector();

		if (filtraNom && filtraCognom && filtraTlf) {
			patients.forEach(p -> {
				if (p.getName().contains(csn) && p.getSurname().contains(css)
						&& p.getPhone().toString().contains(csp)) {
					Vector<Object> row = new Vector<Object>();
					row.add(p.get_id());
					row.add(p.getName());
					row.add(p.getSurname());
					row.add(p.getPhone());
					fileVector.add(row);
				}
			});

		}
		if (!filtraNom && filtraCognom && filtraTlf) {
			patients.forEach(p -> {
				if (p.getSurname().contains(css) && p.getPhone().toString().contains(csp)) {
					Vector<Object> row = new Vector<Object>();
					row.add(p.get_id());
					row.add(p.getName());
					row.add(p.getSurname());

					row.add(p.getPhone());
					fileVector.add(row);
				}
			});

		}
		if (filtraNom && !filtraCognom && filtraTlf)

		{
			patients.forEach(p -> {
				if (p.getName().contains(csn) && p.getPhone().toString().contains(csp)) {
					Vector<Object> row = new Vector<Object>();
					row.add(p.get_id());
					row.add(p.getName());
					row.add(p.getSurname());
					row.add(p.getPhone());
					fileVector.add(row);
				}
			});
		}
		if (filtraNom && filtraCognom && !filtraTlf) {
			patients.forEach(p -> {
				if (p.getName().contains(csn) && p.getSurname().contains(css)) {
					Vector<Object> row = new Vector<Object>();
					row.add(p.get_id());
					row.add(p.getName());
					row.add(p.getSurname());
					row.add(p.getPhone());
					fileVector.add(row);
				}
			});
		}
		if (!filtraNom && !filtraCognom && filtraTlf) {
			patients.forEach(p -> {
				if (p.getPhone().toString().contains(csp)) {
					Vector<Object> row = new Vector<Object>();
					row.add(p.get_id());
					row.add(p.getName());
					row.add(p.getSurname());
					row.add(p.getPhone());
					fileVector.add(row);
				}
			});
		}
		if (!filtraNom && filtraCognom && !filtraTlf) {
			patients.forEach(p -> {
				if (p.getSurname().contains(css)) {
					Vector<Object> row = new Vector<Object>();
					row.add(p.get_id());
					row.add(p.getName());
					row.add(p.getSurname());
					row.add(p.getPhone());
					fileVector.add(row);
				}
			});
		}
		if (filtraNom && !filtraCognom && !filtraTlf) {
			patients.forEach(p -> {
				if (p.getName().contains(csn)) {
					Vector<Object> row = new Vector<Object>();
					row.add(p.get_id());
					row.add(p.getName());
					row.add(p.getSurname());
					row.add(p.getPhone());
					fileVector.add(row);
				}
			});
		}
		return fileVector;
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
	
	public JTextField getSearchName() {
		return searchName;
	}

	public void setSearchName(JTextField searchName) {
		this.searchName = searchName;
	}

	public JTextField getSearchSurname() {
		return searchSurname;
	}

	public void setSearchSurname(JTextField searchSurname) {
		this.searchSurname = searchSurname;
	}

	public JTextField getSearchPhone() {
		return searchPhone;
	}

	public void setSearchPhone(JTextField searchPhone) {
		this.searchPhone = searchPhone;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}


}
