package com.ioc.rastreacovid.screens;

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
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.UpdateUser;
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
	private JLabel res;
	private JFrame frame;

	private String rolselected;

	public UpdateUserScreen(User user) {
		initialize(user);
	}

	// Initialize the contents of the frame.

	public void initialize(User user) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app

		frame.setTitle("Actualizació d'usuari");
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		frame.setTitle("Actualizació d'usuari");
		frame.setBounds(300, 90, 500, 500);

		title = new JLabel("Actualizació");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		title.setSize(235, 30);
		title.setLocation(150, 20);
		frame.add(title);

		name = new JLabel("Nom:");
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		name.setSize(100, 20);
		name.setLocation(100, 80);
		frame.add(name);

		tname = new JTextField();
		tname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tname.setSize(190, 20);
		tname.setLocation(200, 80);
		tname.setText(user.getName());
		frame.add(tname);

		surname = new JLabel("Cognom:");
		surname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		surname.setSize(100, 20);
		surname.setLocation(100, 120);
		frame.add(surname);

		tsurname = new JTextField();
		tsurname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tsurname.setSize(190, 20);
		tsurname.setLocation(200, 120);
		tsurname.setText(user.getSurname());
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
		temail.setText(user.getEmail());
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
		ttlf.setText(user.getPhone().toString());
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
		tpass.setText("*********");
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
		tpassc.setText("*********");
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

		if (user.getType().toString().equalsIgnoreCase("rastreator"))
			rastreator.setSelected(true);
		else
			admin.setSelected(true);

		sub = new JButton("Actualitzar");
		sub.setBackground(new Color(255, 255, 255));
		sub.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		sub.setSize(100, 20);
		sub.setLocation(170, 420);
		sub.addActionListener(this);
		frame.add(sub);
		sub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tpass.getText() != null && tpassc.getText() != null) {
					if (tpass.getText().contains("*") || tpassc.getText().contains("*")
							|| !tpass.getText().equals(tpassc.getText())) {
						JOptionPane.showMessageDialog(null,
								"Les contrassenyes no poden contindre caràcters especials i han de coincidir");
					} else {

						UpdateUser updateUser = new UpdateUser();
						updateUser.setName(tname.getText());
						updateUser.setSurname(tsurname.getText());
						updateUser.setEmail(temail.getText());
						updateUser.setPhone(Integer.parseInt(ttlf.getText()));
						if (rastreator.isSelected()) {
							rolselected = "rastreator";

						} else {
							rolselected = "admin";

						}
						updateUser.setType(rolselected);
						updateUser.setPassword(tpass.getText());
						updateUser.setPasswordConfirm(tpassc.getText());
						ApiConnector.updateUser(token, updateUser);
						JOptionPane.showMessageDialog(null, "S'ha actualitzat correctament");
						frame.dispose();

					}
				} else
					JOptionPane.showMessageDialog(null,
							"Les contrassenyes no poden contindre caràcters especials i han de coincidir");
			}
		});
		res = new JLabel("");
		res.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		res.setSize(500, 25);
		res.setLocation(100, 500);
		frame.add(res);

		frame.setVisible(true);
	}

	public JButton getSub() {
		return sub;
	}

	public void setSub(JButton sub) {
		this.sub = sub;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

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
	
	public JTextField getTpass() {
		return tpass;
	}

	public void setTpass(JTextField tpass) {
		this.tpass = tpass;
	}

	public JTextField getTpassc() {
		return tpassc;
	}

	public void setTpassc(JTextField tpassc) {
		this.tpassc = tpassc;
	}


}
