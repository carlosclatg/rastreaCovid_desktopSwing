package com.ioc.rastreacovid.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	private Panel panel;
	private JTable table;
	private JLabel titledelete;

	public DeleteUserScreen() {
		initialize();
	}

// Initialize the contents of the frame.

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);

		titledelete = new JLabel("Fes doble click per eliminar un usuari");
		titledelete.setHorizontalAlignment(SwingConstants.CENTER);
		titledelete.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		frame.getContentPane().add(titledelete, BorderLayout.NORTH);
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		frame.setTitle("Llista d'usuaris");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

// We will display the users in a list with the columns corresponding to the information we need
// Define the columns
		Vector columnNames = new Vector();
		columnNames.add("ID");
		columnNames.add("Nom");
		columnNames.add("Cognom");
		columnNames.add("Email");
		columnNames.add("Rol");
		columnNames.add("Telèfon");
		Vector fileVector = new Vector();

// Consult the existing users and list them with columns
		List<User> users = ApiConnector.getUsers(token);
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

// Define the table
		table = new JTable(fileVector, columnNames);
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
					//DeleteUserScreen dus = new DeleteUserScreen();
					//dus.getFrame().setVisible();

					if (id != null) {
						JOptionPane.showMessageDialog(null, "L'usuari s'ha eliminat correctament");
					}

				}
			}
		}

		);

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
