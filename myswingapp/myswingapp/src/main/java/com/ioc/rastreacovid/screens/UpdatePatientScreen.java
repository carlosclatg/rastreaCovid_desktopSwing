package com.ioc.rastreacovid.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.ContactWithoutId;
import com.ioc.rastreacovid.mappers.Patient;
import com.ioc.rastreacovid.mappers.UpdatePacient;
import com.ioc.rastreacovid.mappers.User;
import com.ioc.rastreacovid.mappers.UserPost;
import java.awt.BorderLayout;

public class UpdatePatientScreen implements ActionListener {

	// Components of the Form
	private JLabel title;
	private JLabel name;
	private JLabel surname;
	private JTextField tname;
	private JTextField tsurname;
	private JLabel dateb;
	private JTextField tdateb;
	private JLabel tlf;
	private JTextField ttlf;
	private JLabel pcrd;
	private JTextField tpcrd;
	private JLabel sintomsLbl;
	private JLabel contactesLbl;
	private JTextField[] sintomaJt;
	private JTextField[] contacteJt;

	private JRadioButton admin;
	private JRadioButton rastreator;
	private ButtonGroup rolegp;
	private JButton sub;
	private JLabel res;
	private JFrame frame;
	private Patient patient;

	private String rolselected;

	public UpdatePatientScreen(Patient patient) {
		initialize(patient);
	}

	// Initialize the contents of the frame.

	public void initialize(Patient patient) {
		GridBagConstraints c = new GridBagConstraints();

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		frame.setTitle("Actualizació d'usuari");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		frame.setTitle("Actualització de Pacient");
		frame.setBounds(300, 90, 500, 500);
		frame.setLayout(new GridBagLayout());

		title = new JLabel("Actualització de pacient");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		title.setSize(235, 30);
		title.setLocation(150, 20);
		frame.getContentPane().add(title);
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		frame.getContentPane().add(title, c);

		name = new JLabel("Nom:");
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		name.setSize(100, 20);
		name.setLocation(100, 80);
		frame.getContentPane().add(name);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		frame.getContentPane().add(name, c);

		tname = new JTextField();
		tname.setText(patient.getName());
		tname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tname.setSize(190, 20);
		tname.setLocation(200, 80);
		frame.getContentPane().add(tname);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		frame.getContentPane().add(tname, c);

		surname = new JLabel("Cognom:");
		surname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		surname.setSize(100, 20);
		surname.setLocation(100, 120);
		frame.getContentPane().add(surname);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		frame.getContentPane().add(surname, c);

		tsurname = new JTextField();
		tsurname.setText(patient.getSurname());
		tsurname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tsurname.setSize(190, 20);
		tsurname.setLocation(200, 120);
		frame.getContentPane().add(tsurname);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		frame.getContentPane().add(tsurname, c);

		tlf = new JLabel("Telèfon:");
		tlf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		tlf.setSize(100, 20);
		tlf.setLocation(100, 160);
		frame.getContentPane().add(tlf);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		frame.getContentPane().add(tlf, c);

		ttlf = new JTextField();
		ttlf.setText(patient.getPhone().toString());
		ttlf.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		ttlf.setSize(190, 20);
		ttlf.setLocation(200, 160);
		frame.getContentPane().add(ttlf);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		frame.getContentPane().add(ttlf, c);

		dateb = new JLabel("Data Naixament:");
		dateb.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		dateb.setSize(100, 20);
		dateb.setLocation(100, 200);
		frame.getContentPane().add(dateb);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		frame.getContentPane().add(dateb, c);

		tdateb = new JTextField();
		tdateb.setText(patient.getBirthdate().toString());
		tdateb.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tdateb.setSize(190, 20);
		tdateb.setLocation(200, 200);
		frame.getContentPane().add(tdateb);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 4;
		frame.getContentPane().add(tdateb, c);

		pcrd = new JLabel("Data PCR");
		pcrd.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		pcrd.setSize(100, 20);
		pcrd.setLocation(100, 240);
		frame.getContentPane().add(pcrd);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		frame.getContentPane().add(pcrd, c);

		tpcrd = new JTextField();
		tpcrd.setText(patient.getPCRDate().toString());
		tpcrd.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tpcrd.setSize(190, 20);
		tpcrd.setLocation(200, 240);
		frame.getContentPane().add(tpcrd);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 5;
		frame.getContentPane().add(tpcrd, c);

		int ultimgridY = 0;
		int ultimYLocation = 0;

		sintomaJt = new JTextField[patient.getSintoms().size()];
		for (int i = 0; i < patient.getSintoms().size(); i++) {

			sintomsLbl = new JLabel("Símptoma " + (i + 1));
			sintomsLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			sintomsLbl.setSize(100, 20);
			sintomsLbl.setLocation(100, 240 + ((i + 1) * 40));
			frame.getContentPane().add(sintomsLbl);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 6 + i;
			frame.getContentPane().add(sintomsLbl, c);

			// sintomaJt

			sintomaJt[i] = new JTextField();
			sintomaJt[i].setText(patient.getSintoms().get(i));
			sintomaJt[i].setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			sintomaJt[i].setSize(190, 20);
			sintomaJt[i].setLocation(200, 240 + ((i + 1) * 40));
			sintomaJt[i].setEditable(false);
			c.weightx = 0.5;
			c.gridx = 1;
			c.gridy = 6 + i;
			ultimgridY = c.gridy;
			ultimYLocation = 240 + ((i + 1) * 40);
			frame.getContentPane().add(sintomaJt[i], c);
		}

		contacteJt = new JTextField[patient.getContacts().size()];
		for (int i = 0; i < patient.getContacts().size(); i++) {

			contactesLbl = new JLabel("Contacte " + (i + 1));
			contactesLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			contactesLbl.setSize(100, 20);
			contactesLbl.setLocation(100, ultimYLocation + ((i + 1) * 40));
			frame.getContentPane().add(contactesLbl);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = ultimgridY + (i + 1);
			frame.getContentPane().add(contactesLbl, c);

			// sintomaJt

			contacteJt[i] = new JTextField();
			contacteJt[i].setText(patient.getContacts().get(i));
			System.out.println(contacteJt[i].getText());
			contacteJt[i].setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			contacteJt[i].setSize(190, 20);
			contacteJt[i].setLocation(200, ultimYLocation + ((i + 1) * 40));
			contacteJt[i].setEditable(false);
			c.weightx = 0.5;
			c.gridx = 1;
			c.gridy = ultimgridY + (i + 1);
			frame.getContentPane().add(contacteJt[i], c);

		}

		// contacteJt
		/*
		 * String[] sintomas = new String[patient.getSintoms().size()]; for (int i = 0;
		 * i < patient.getSintoms().size(); i++) {
		 * 
		 * sintomsLbl = new JLabel("Contacte "+i); sintomsLbl.setFont(new
		 * Font("Lucida Grande", Font.PLAIN, 15)); sintomsLbl.setSize(100, 20);
		 * sintomsLbl.setLocation(100, 240+((i+1)*40));
		 * frame.getContentPane().add(sintomsLbl); c.fill =
		 * GridBagConstraints.HORIZONTAL; c.gridx = 0; c.gridy = 6+i;
		 * frame.getContentPane().add(sintomsLbl, c);
		 * 
		 * //sintomaJt
		 * 
		 * sintomaJt = new JTextField(); sintomaJt.setText(patient.getSintoms().get(i));
		 * sintomaJt.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		 * sintomaJt.setSize(190, 20); sintomaJt.setLocation(200, 240); c.weightx = 0.5;
		 * c.gridx = 1; c.gridy = 6+i; frame.getContentPane().add(sintomaJt);
		 * 
		 * 
		 * 
		 * }
		 */

		// JList<String> sintomList = new JList<String>(sintomas);

		/*
		 * sintomsLbl = new JLabel("Símptoma "+i); sintomsLbl.setFont(new
		 * Font("Lucida Grande", Font.PLAIN, 15)); sintomsLbl.setSize(100, 20);
		 * sintomsLbl.setLocation(100, 240); frame.getContentPane().add(sintomsLbl);
		 * c.fill = GridBagConstraints.HORIZONTAL; c.gridx = 0; c.gridy = 6;
		 * frame.getContentPane().add(sintomsLbl, c);
		 * 
		 * 
		 * c.ipady = 30; // make this component tall c.weightx = 0.0; c.gridwidth = 30;
		 * c.gridx = 1; c.gridy = 6; frame.getContentPane().add(sintomList);
		 * sintomList.setVisible(true);
		 * 
		 * contactesLbl = new JLabel("Contactes"); contactesLbl.setFont(new
		 * Font("Lucida Grande", Font.PLAIN, 15)); contactesLbl.setSize(100, 20);
		 * contactesLbl.setLocation(100, 240); frame.getContentPane().add(contactesLbl);
		 * c.fill = GridBagConstraints.HORIZONTAL; c.gridx = 0; c.gridy = 7;
		 * frame.getContentPane().add(contactesLbl, c);
		 * 
		 * String[] contactesL = new String[patient.getContacts().size()]; for (int i =
		 * 0; i < patient.getContacts().size(); i++) { contactesL[i] =
		 * patient.getContacts().get(i); } JList<String> contactesList = new
		 * JList<String>(contactesL);
		 * 
		 * c.ipady = 30; // make this component tall c.weightx = 0.0; c.gridwidth = 30;
		 * c.gridx = 1; c.gridy = 7; frame.getContentPane().add(contactesList);
		 * contactesList.setVisible(true);
		 * 
		 * c.fill = GridBagConstraints.HORIZONTAL;
		 */

		// frame.getContentPane().add(sintomas, c);

		sub = new JButton("Actualitzar");
		sub.setBackground(new Color(255, 255, 255));
		sub.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		sub.setSize(100, 20);
		sub.setLocation(130, 420);
		sub.addActionListener(this);
		frame.getContentPane().add(sub);
		sub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				UpdatePacient up = new UpdatePacient();
				List<ContactWithoutId> cwil = new ArrayList<ContactWithoutId>();
				List<String> ssin = new ArrayList<String>();
				up.set_id(patient.get_id());
				up.setName(tname.getText());
				up.setSurname(tsurname.getText());
				up.setPhone(Integer.parseInt(ttlf.getText()));
				up.setPCRDate(new Date().toInstant().getEpochSecond());
				up.setBirthDate(new Date().toInstant().getEpochSecond());
			
			
				
				ContactWithoutId cwi = null;
				for(int i=0;i<contacteJt.length;i++){
				cwi = new ContactWithoutId();
				cwi.setName(contacteJt[i].getText());
				cwil.add(cwi);
				}
				up.setContacts(cwil);
				
				for(int i=0;i<sintomaJt.length;i++){
					ssin.add(sintomaJt[i].getText());
					}
				up.setSintoms(ssin);
				
				String ok = ApiConnector.updatePacient(token, up);
				
				
			/*	List<ContactWithoutId> list = new ArrayList<ContactWithoutId>();
				up.setContacts(list);
				List<String> listsintoms = new ArrayList<String>();
				listsintoms.add("60684bcdc609df2b79a8879d");
				up.setSintoms(listsintoms);
				String ok = ApiConnector.updatePacient(token, "6099879ed175851f8b512e1d", updatePacient);

				UserPost up = new UserPost();
				up.setName(tname.getText());
				up.setSurname(tsurname.getText());
				up.setPhone(Integer.parseInt(ttlf.getText()));
				// ApiConnector.updateUser(token, up);
*/
			
			}	
		});

		res = new JLabel("");
		res.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		res.setSize(500, 25);
		res.setLocation(100, 500);
		frame.getContentPane().add(res);

		frame.setVisible(true);

		c.fill = GridBagConstraints.CENTER;
		c.ipady = 5;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 9;
		frame.getContentPane().add(sub, c);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Esbozo de método generado automáticamente

	}

	public Window getFrame() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

}