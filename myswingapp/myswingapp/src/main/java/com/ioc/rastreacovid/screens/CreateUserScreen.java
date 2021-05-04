package com.ioc.rastreacovid.screens;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JRadioButton;

public class CreateUserScreen implements ActionListener {

	// Components of the Form
	private JLabel title;
	private JLabel name;
	private JLabel surname;
	private JTextField tname;
	private JTextField tsurname;
	private JLabel email;
	private JTextField temail;
	private JLabel tlf;
	private JTextField ttlf;
	private JLabel role;
	private JRadioButton admin;
	private JRadioButton rastreator;
	private ButtonGroup rolegp;
	private JButton sub;
	private JButton reset;
	private JLabel res;
	private JFrame frame;

	public CreateUserScreen() {
		initialize();
	}

	// Initialize the contents of the frame.

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		frame.setTitle("Creació d'usuari");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		frame.setTitle("Creació d'usuari");
		frame.setBounds(300, 90, 500, 500);

		title = new JLabel("Creació d'usuari");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		title.setSize(235, 30);
		title.setLocation(150, 30);
		frame.add(title);

		name = new JLabel("Nom:");
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		name.setSize(100, 20);
		name.setLocation(100, 100);
		frame.add(name);

		tname = new JTextField();
		tname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tname.setSize(190, 20);
		tname.setLocation(200, 100);
		frame.add(tname);

		surname = new JLabel("Cognom:");
		surname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		surname.setSize(100, 20);
		surname.setLocation(100, 150);
		frame.add(surname);

		tsurname = new JTextField();
		tsurname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tsurname.setSize(190, 20);
		tsurname.setLocation(200, 150);
		frame.add(tsurname);

		email = new JLabel("Email:");
		email.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		email.setSize(100, 20);
		email.setLocation(100, 200);
		frame.add(email);

		temail = new JTextField();
		temail.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		temail.setSize(190, 20);
		temail.setLocation(200, 200);
		frame.add(temail);

		tlf = new JLabel("Telèfon:");
		tlf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		tlf.setSize(100, 20);
		tlf.setLocation(100, 250);
		frame.add(tlf);

		ttlf = new JTextField();
		ttlf.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		ttlf.setSize(190, 20);
		ttlf.setLocation(200, 250);
		frame.add(ttlf);

		role = new JLabel("Rol:");
		role.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		role.setSize(100, 20);
		role.setLocation(100, 300);
		frame.add(role);

		admin = new JRadioButton("Admin");
		admin.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		admin.setSelected(true);
		admin.setSize(106, 20);
		admin.setLocation(200, 300);
		frame.add(admin);

		rastreator = new JRadioButton("Rastreator");
		rastreator.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		rastreator.setSelected(false);
		rastreator.setSize(125, 20);
		rastreator.setLocation(200, 350);
		frame.add(rastreator);

		rolegp = new ButtonGroup();
		rolegp.add(admin);
		rolegp.add(rastreator);

		sub = new JButton("Aceptar");
		sub.setBackground(new Color(255, 255, 255));
		sub.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		sub.setSize(100, 20);
		sub.setLocation(130, 410);
		sub.addActionListener(this);
		frame.add(sub);

		reset = new JButton("Reset");
		reset.setBackground(new Color(255, 255, 255));
		reset.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		reset.setSize(100, 20);
		reset.setLocation(250, 410);
		reset.addActionListener(this);
		frame.add(reset);

		res = new JLabel("");
		res.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		res.setSize(500, 25);
		res.setLocation(100, 500);
		frame.add(res);

		frame.setVisible(true);
	}

	// method actionPerformed()
	// to get the action performed
	// by the user and act accordingly
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == reset) {
			String def = "";
			tname.setText(def);
			tsurname.setText(def);
			temail.setText(def);
			ttlf.setText(def);

		}

		else if (e.getSource() == sub) {
			tname.setText(tname.getText());
			tsurname.setText(tsurname.getText());
			temail.setText(tsurname.getText());
			ttlf.setText(tsurname.getText());
		}
	}

	public Window getFrame() {
		return frame;
	}

	public JTextField getTname() {
		return tname;
	}

	public void setTname(JTextField tname) {
		this.tname = tname;
	}

}
