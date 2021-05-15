package com.ioc.rastreacovid.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.DeleteUser;
import com.ioc.rastreacovid.mappers.PatientDetail;
import com.ioc.rastreacovid.mappers.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class DeleteUserScreen {

	private JFrame frame;

	private JTable table;

	private JTextField searchName;
	private JLabel lblname;
	private JTextField searchSurname;
	private JLabel lblSurname;
	private JTextField searchEmail;
	private JLabel lblEmail;
	private JButton searchButton;
	private JLabel doubleClick;

	private List<User> users;
	private Vector fileVector;
	private Vector columnNames;
	private String cadenaN;
	private String cadenaS;
	private String cadenaE;

	public DeleteUserScreen() {

		initialize();
	}

	public DeleteUserScreen(Vector fileVector, String cadenaN, String cadenaS, String cadenaE) {
		this.fileVector = fileVector;
		this.cadenaN = cadenaN;
		this.cadenaS = cadenaS;
		this.cadenaE = cadenaE;

		initialize();
	}

// Initialize the contents of the frame.

	public void initialize() {
		GridBagConstraints c = new GridBagConstraints();

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		searchName = new JTextField(cadenaN != null ? cadenaN : "");
		searchSurname = new JTextField(cadenaS != null ? cadenaS : "");
		searchEmail = new JTextField(cadenaE != null ? cadenaE : "");
		lblname = new JLabel("Buscar per nom:");
		lblSurname = new JLabel("Buscar per cognom:");
		lblEmail = new JLabel("Buscar per email:");

		doubleClick = new JLabel("Fes doble click per eliminar un usuari");
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
		frame.getContentPane().add(lblEmail, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		frame.getContentPane().add(searchEmail, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		frame.getContentPane().add(doubleClick, c);

		searchName.setColumns(10);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		searchButton = new JButton("Buscar");

		users = new ArrayList();

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileVector.clear();
				boolean filtraNom = false;
				boolean filtraCognom = false;
				boolean filtraEmail = false;
				CharSequence csn = searchName.getText(); // String is already a CharSequence
				CharSequence css = searchSurname.getText();
				CharSequence cse = searchEmail.getText();
				if (csn.length() > 0)
					filtraNom = true;
				if (css.length() > 0)
					filtraCognom = true;
				if (cse.length() > 0)
					filtraEmail = true;
				if (filtraNom || filtraCognom || filtraEmail)
					fileVector = filtrar(filtraNom, filtraCognom, filtraEmail, csn, css, cse);

				DeleteUserScreen screen = new DeleteUserScreen(fileVector, searchName.getText(),
						searchSurname.getText(), searchEmail.getText());
				screen.getFrame().setVisible(true);
				frame.dispose();

			}
		});

		frame.setTitle("Llista d'usuaris");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

// We will display the users in a list with the columns corresponding to the information we need
// Define the columns
		columnNames = new Vector();
		columnNames.add("ID");
		columnNames.add("Nom");
		columnNames.add("Cognom");
		columnNames.add("Email");
		columnNames.add("Rol");
		columnNames.add("Telèfon");

		// Consult the existing patients and list them with columns
		if (fileVector == null || (fileVector != null && fileVector.size() == 0)) {
			fileVector = new Vector();
			users = ApiConnector.getUsers(token);
			System.out.println(users.size());

			users.forEach(u -> {
				Vector<Object> row = new Vector<Object>();
				row.add(u.get_id());
				row.add(u.getName());
				row.add(u.getSurname());
				row.add(u.getEmail());
				row.add(u.getType());
				row.add(u.getPhone());
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

				// If you double click deletes a user.
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
					String token = prefs.get("token", "token");
					System.out.println(token);

					int row2 = table.getSelectedRow();
					String id = (String) table.getValueAt(row2, 0);
					String deleteUser = ApiConnector.deleteUser(token, id);
					DeleteUserScreen dus = new DeleteUserScreen();
					dus.getFrame().setVisible(true);

					if (id != null) {
						JOptionPane.showMessageDialog(null, "L'usuari s'ha eliminat correctament");

					}

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
		c.gridy = 5;

		frame.getContentPane().add(jScrollPane, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		frame.getContentPane().add(searchButton, c);
	}

	private Vector filtrar(boolean filtraNom, boolean filtraCognom, boolean filtraEmail, CharSequence csn,
			CharSequence css, CharSequence cse) {
		Vector fileVector = new Vector();

		if (filtraNom && filtraCognom && filtraEmail) {
			users.forEach(u -> {
				if (u.getName().contains(csn) && u.getSurname().contains(css) && u.getEmail().contains(cse)) {
					Vector<Object> row = new Vector<Object>();
					row.add(u.get_id());
					row.add(u.getName());
					row.add(u.getSurname());
					row.add(u.getEmail());
					row.add(u.getType());
					row.add(u.getPhone());
					fileVector.add(row);
				}
			});
		}
		if (!filtraNom && filtraCognom && filtraEmail) {
			users.forEach(u -> {
				if (u.getSurname().contains(css) && u.getEmail().contains(cse)) {
					Vector<Object> row = new Vector<Object>();
					row.add(u.get_id());
					row.add(u.getName());
					row.add(u.getSurname());
					row.add(u.getEmail());
					row.add(u.getType());
					row.add(u.getPhone());
					fileVector.add(row);
				}
			});
		}
		if (filtraNom && !filtraCognom && filtraEmail) {
			users.forEach(u -> {
				if (u.getName().contains(csn) && u.getEmail().contains(cse)) {
					Vector<Object> row = new Vector<Object>();
					row.add(u.get_id());
					row.add(u.getName());
					row.add(u.getSurname());
					row.add(u.getEmail());
					row.add(u.getType());
					row.add(u.getPhone());
					fileVector.add(row);
				}
			});
		}
		if (filtraNom && filtraCognom && !filtraEmail) {
			users.forEach(u -> {
				if (u.getName().contains(csn) && u.getSurname().contains(css)) {
					Vector<Object> row = new Vector<Object>();
					row.add(u.get_id());
					row.add(u.getName());
					row.add(u.getSurname());
					row.add(u.getEmail());
					row.add(u.getType());
					row.add(u.getPhone());
					fileVector.add(row);
				}
			});
		}

		if (!filtraNom && !filtraCognom && filtraEmail) {
			users.forEach(u -> {
				if (u.getEmail().contains(cse)) {
					Vector<Object> row = new Vector<Object>();
					row.add(u.get_id());
					row.add(u.getName());
					row.add(u.getSurname());
					row.add(u.getEmail());
					row.add(u.getType());
					row.add(u.getPhone());
					fileVector.add(row);
				}
			});
		}
		if (!filtraNom && filtraCognom && !filtraEmail) {
			users.forEach(u -> {
				if (u.getSurname().contains(css)) {
					Vector<Object> row = new Vector<Object>();
					row.add(u.get_id());
					row.add(u.getName());
					row.add(u.getSurname());
					row.add(u.getEmail());
					row.add(u.getType());
					row.add(u.getPhone());
					fileVector.add(row);
				}
			});
		}
		if (filtraNom && !filtraCognom && !filtraEmail) {
			users.forEach(u -> {
				if (u.getName().contains(csn)) {
					Vector<Object> row = new Vector<Object>();
					row.add(u.get_id());
					row.add(u.getName());
					row.add(u.getSurname());
					row.add(u.getEmail());
					row.add(u.getType());
					row.add(u.getPhone());
					fileVector.add(row);
				}
			});
		}

		return fileVector;
	}

//Generation of getters & setters
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