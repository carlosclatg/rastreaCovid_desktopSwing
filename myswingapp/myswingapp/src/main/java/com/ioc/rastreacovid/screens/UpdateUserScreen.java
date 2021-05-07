/*package com.ioc.rastreacovid.screens;

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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ioc.rastreacovid.mappers.User;



public class UpdateUserScreen implements ActionListener {

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
	private JLabel pass;
	private JTextField tpass;
	private JLabel passc;
	private JTextField tpassc;
	private JLabel role;
	private JRadioButton admin;
	private JRadioButton rastreator;
	private ButtonGroup rolegp;
	private JButton sub;
	private JButton reset;
	private JLabel res;
	private JFrame frame;
	
	private String rolselected;

	public UpdateUserScreen(User user) {
		initialize(user);
	}

	// Initialize the contents of the frame.

	public void initialize( User user) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		frame.setTitle("Actualizació d'usuari");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		frame.setTitle("Actualizació d'usuari");
		frame.setBounds(300, 90, 500, 500);

		title = new JLabel("Creació d'usuari");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		title.setSize(235, 30);
		title.setLocation(150, 20);
		frame.add(title);

		name = new JLabel("Nom:");
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		name.setSize(100, 20);
		name.setLocation(100, 80);
		frame.add(name);
		
		
		// We show the "Nom" label and the patient's name
		
		

		//tname = new JTextField();
		tname.setText(user.getName());	
		tname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tname.setSize(190, 20);
		tname.setLocation(200, 80);
		frame.add(tname);

		surname = new JLabel("Cognom:");
		surname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		surname.setSize(100, 20);
		surname.setLocation(100, 120);
		frame.add(surname);

		tsurname = new JTextField();
		tsurname.setText(user.getSurname());
		tsurname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tsurname.setSize(190, 20);
		tsurname.setLocation(200, 120);
		frame.add(tsurname);

		email = new JLabel("Email:");
		email.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		email.setSize(100, 20);
		email.setLocation(100, 160);
		frame.add(email);

		temail = new JTextField();
		temail.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		temail.setSize(190, 20);
		temail.setLocation(200, 160);
		frame.add(temail);

		tlf = new JLabel("Telèfon:");
		tlf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		tlf.setSize(100, 20);
		tlf.setLocation(100, 200);
		frame.add(tlf);

		ttlf = new JTextField();
		ttlf.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		ttlf.setSize(190, 20);
		ttlf.setLocation(200, 200);
		frame.add(ttlf);

		pass = new JLabel("Contrasenya:");
		pass.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		pass.setSize(100, 20);
		pass.setLocation(100, 240);
		frame.add(pass);

		tpass = new JTextField();
		tpass.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tpass.setSize(190, 20);
		tpass.setLocation(200, 240);
		frame.add(tpass);

		passc = new JLabel("Confirmació de Contrasenya:");
		passc.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		passc.setSize(100, 20);
		passc.setLocation(100, 280);
		frame.add(passc);

		tpassc = new JTextField();
		tpassc.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tpassc.setSize(190, 20);
		tpassc.setLocation(200, 280);
		frame.add(tpassc);

		role = new JLabel("Rol:");
		role.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		role.setSize(100, 20);
		role.setLocation(100, 320);
		frame.add(role);

		admin = new JRadioButton("Admin");
		admin.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		admin.setSelected(true);
		admin.setSize(106, 20);
		admin.setLocation(200, 320);
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
		sub.setLocation(130, 420);
		sub.addActionListener(this);
		frame.add(sub);

		reset = new JButton("Reset");
		reset.setBackground(new Color(255, 255, 255));
		reset.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		reset.setSize(100, 20);
		reset.setLocation(250, 420);
		reset.addActionListener(this);
		frame.add(reset);

		res = new JLabel("");
		res.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		res.setSize(500, 25);
		res.setLocation(100, 500);
		frame.add(res);

		frame.setVisible(true);
	
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

*/