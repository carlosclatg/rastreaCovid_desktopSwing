package com.ioc.rastreacovid.screens;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.Patient;
import com.ioc.rastreacovid.mappers.PatientDetail;
import com.ioc.rastreacovid.mappers.Sintom;
import com.ioc.rastreacovid.mappers.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
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

public class SymptomsScreen {

	private JFrame frame;
	private Panel panel;
	private JTable table;
	private TableRowSorter trsfiltro;
	private JTextField search;
	private JLabel lsearch;
	private JLabel lblNewLabel;


	public SymptomsScreen() {
		initialize();
	}

	// Initialize the contents of the frame.

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		

		frame.setTitle("Llista d'usuaris");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");
		
		
		Vector columnNames = new Vector();
		columnNames.add("ID");

		Vector fileVector = new Vector();
		
		

		String cat = null;
		String lang = cat;
		// Consult the existing patients and list them with columns
		List<Sintom> sintom = ApiConnector.getAllSintoms(token, lang)
;		System.out.println(sintom.size());

			sintom.forEach(u -> {
			Vector<Object> row = new Vector<Object>();
			row.add(u.getSintoma_cat());


			fileVector.add(row);
		});

		// Define the table
		table = new JTable(fileVector, columnNames);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				// If you double click update user
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
					String token = prefs.get("token", "token");
					System.out.println(token);
					//User user = ApiConnector.getUsers(token,(String) table.getValueAt(table.getSelectedRow(), 0));
					Vector userVector = (Vector) fileVector.get(table.getSelectedRow());
					User user = new User();
					user.set_Id(userVector.get(0).toString());
					user.setName(userVector.get(1).toString());
					user.setSurname(userVector.get(2).toString());
					user.setEmail(userVector.get(3).toString());
					user.setType(userVector.get(4).toString());
					user.setPhone((Integer)userVector.get(5));
					
					
					
					System.out.println(user);
					UpdateUserScreen pds = new UpdateUserScreen(user);
					pds.getFrame().setVisible(true);
				} 
				
			}
		
		});
	}
}
		
		
		
		