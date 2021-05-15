package com.ioc.rastreacovid.screens;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InfoScreen {

	private JFrame frame;
	private Panel panel;

	private JLabel casesLbl;
	private JButton casesButton;
	private JLabel wathLbl;
	private JButton whatButton;
	private JLabel symLbl;
	private JButton symButton;
	private JLabel trackerLbl;
	private JButton trackerButton;
	private JButton closeButton;

	// Create the application.
	public InfoScreen() {
		initialize();
	}

	// Initialize the contents of the frame.

	public void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 550, 360);
		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		wathLbl = new JLabel("Què és el Covid-19?");
		wathLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		wathLbl.setBounds(38, 39, 163, 16);
		frame.getContentPane().add(wathLbl);

		whatButton = new JButton("Navega");
		whatButton.setBounds(204, 35, 117, 29);
		frame.getContentPane().add(whatButton);
		whatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://es.wikipedia.org/wiki/COVID-19"));
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error, no es pot obrir l'enllaç");
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}

			}
		});

		casesLbl = new JLabel("Nou casos i defuncions en Catalunya");
		casesLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		casesLbl.setBounds(38, 83, 294, 16);
		frame.getContentPane().add(casesLbl);

		casesButton = new JButton("Navega");
		casesButton.setBounds(319, 79, 117, 29);
		frame.getContentPane().add(casesButton);
		casesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(
							"https://www.google.com/search?q=estadisticas+coronavirus&sxsrf=ALeKk00vs4AIPzlIAhZTbvP_-tF5ktYp1g%3A1621100325509&ei=JQegYMfSHqeYlwSUnaGwAg&oq=estadics&gs_lcp=Cgdnd3Mtd2l6EAEYADIKCAAQsQMQyQMQCjIFCAAQkgMyBQgAEJIDMgoIABCxAxCDARAKMgcIABCxAxAKMgcIABCxAxAKMgQIABAKMgoIABCxAxCDARAKMgQIABAKMgcIABCxAxAKOgUIABCxAzoHCAAQhwIQFDoCCAA6BQgAEMkDOgQIIxAnOgQIABBDOggIABCxAxCDAToICC4QsQMQgwE6BQguELEDOgIILjoECC4QQzoHCAAQyQMQQzoKCC4QsQMQgwEQQzoKCAAQhwIQsQMQFDoICAAQsQMQyQM6CwgAELEDEMcBEK8BUMWdG1irrBtg67kbaAFwAHgAgAHCAYgBqAqSAQM0LjeYAQCgAQGqAQdnd3Mtd2l6wAEB&sclient=gws-wiz"));
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error, no es pot obrir l'enllaç");
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}

			}
		});

		symLbl = new JLabel("Símptomes");
		symLbl.setBounds(38, 135, 110, 16);
		symLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		frame.getContentPane().add(symLbl);

		symButton = new JButton("Navega");
		symButton.setBounds(120, 131, 117, 29);
		frame.getContentPane().add(symButton);
		symButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(
							"https://www.google.com/search?q=s%C3%ADntomas+del+coronavirus&sxsrf=ALeKk00vs4AIPzlIAhZTbvP_-tF5ktYp1g%3A1621100325509&ei=JQegYMfSHqeYlwSUnaGwAg&oq=estadics&gs_lcp=Cgdnd3Mtd2l6EAEYADIKCAAQsQMQyQMQCjIFCAAQkgMyBQgAEJIDMgoIABCxAxCDARAKMgcIABCxAxAKMgcIABCxAxAKMgQIABAKMgoIABCxAxCDARAKMgQIABAKMgcIABCxAxAKOgUIABCxAzoHCAAQhwIQFDoCCAA6BQgAEMkDOgQIIxAnOgQIABBDOggIABCxAxCDAToICC4QsQMQgwE6BQguELEDOgIILjoECC4QQzoHCAAQyQMQQzoKCC4QsQMQgwEQQzoKCAAQhwIQsQMQFDoICAAQsQMQyQM6CwgAELEDEMcBEK8BUMWdG1irrBtg67kbaAFwAHgAgAHCAYgBqAqSAQM0LjeYAQCgAQGqAQdnd3Mtd2l6wAEB&sclient=gws-wiz&stick=H4sIAAAAAAAAAONgVuLVT9c3NMwySk6OL8zJecTozS3w8sc9YSmnSWtOXmO04eIKzsgvd80rySypFNLjYoOyVLgEpVB1ajBI8XOhCvHsYuLzSE3MKckIrswtKMnPLV7EKll8eG0ekJlYrJCSmqOQnF-Un5dYlllUWgwAMyAGPowAAAA&ictx=1&ved=2ahUKEwiO_efMn8zwAhVBqxoKHR0VCA8QyNoBKAJ6BAgPEAg"));
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error, no es pot obrir l'enllaç");
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}

			}
		});

		trackerLbl = new JLabel("Tracker Espanya");
		trackerLbl.setBounds(38, 183, 123, 16);
		frame.getContentPane().add(trackerLbl);
		trackerLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		trackerButton = new JButton("Navega");
		trackerButton.setBounds(171, 179, 117, 29);
		frame.getContentPane().add(trackerButton);
		trackerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(
							"https://graphics.reuters.com/world-coronavirus-tracker-and-maps/es/countries-and-territories/spain/"));
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error, no es pot obrir l'enllaç");
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}

			}
		});

		closeButton = new JButton("Tancar");
		closeButton.setBounds(6, 297, 538, 29);
		frame.getContentPane().add(closeButton);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

	}

	public Window getFrame() {
		return frame;
	}
}
