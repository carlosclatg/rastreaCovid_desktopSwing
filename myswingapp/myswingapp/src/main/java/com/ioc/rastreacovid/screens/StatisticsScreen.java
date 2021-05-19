package com.ioc.rastreacovid.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.CountSintom;
import com.ioc.rastreacovid.mappers.Stats;

//Class to be able to show the different patient statistics and symptoms.
public class StatisticsScreen {

	private JFrame frame;
	private JButton frequencyButton;
	private JButton statsButton;
	private JButton statsPButton;

	// Create the application.
	public StatisticsScreen() {
		initialize();
	}

	// Initialize the contents of the frame.

	public void initialize() {

		Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
		String token = prefs.get("token", "token");

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(92, 255, 208));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(92, 255, 208));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setBackground(new Color(92, 255, 208));
		frame.setBounds(100, 100, 500, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, otherwise closes all the app
		frame.setTitle("Estadístiques");

		// Button to display the graph of the frequency of the symptoms.
		frequencyButton = new JButton("Freqüència de Símptomes");
		frequencyButton.setBounds(148, 282, 200, 50);
		panel.add(frequencyButton);
		frequencyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Charts");
				frame.setSize(1050, 500);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);

				generatefrequencySintoms(frame, token);

			}
		});

		// Button to display the graph of symptom statistics
		statsButton = new JButton("Estadístiques de Símptomes");
		statsButton.setBounds(148, 282, 200, 50);
		panel.add(statsButton);
		statsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Charts");
				frame.setSize(1050, 500);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);

				generateStatsSintoms(frame, token);

			}
		});
		// Button to display the graph of patients statistics
		statsPButton = new JButton("Estadístiques de Pacients");
		statsPButton.setBounds(148, 282, 200, 50);
		panel.add(statsPButton);
		statsPButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Charts");
				frame.setSize(1050, 500);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);

				generateStatsPacients(frame, token);

			}
		});
	}

	// Method that generates the symptom frequency chart.
	private void generatefrequencySintoms(JFrame frame, String token) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		System.out.println("g");
		List<CountSintom> countSintomList = ApiConnector.getFrequency(token, "cat");
		if (countSintomList != null && countSintomList.size() > 0) {
			countSintomList.forEach(x -> {
				dataset.addValue(x.getCount(), "Símptoma", x.get_id());
			});
		}

		JFreeChart chart = ChartFactory.createBarChart("Freqüència de Símptones", "Símptomes", "Freqüencia", dataset);
		ChartPanel cp = new ChartPanel(chart);
		frame.getContentPane().add(cp);

	}

	// Method that generates patient statistics graphs.
	private void generateStatsPacients(JFrame frame, String token) {
		List<Stats> stats = ApiConnector.getStats(token);
		if (stats != null && stats.size() > 0) {
			HistogramDataset dataset = new HistogramDataset();
			List<Double> listcontacts = new ArrayList<Double>();
			stats.forEach(x -> {
				listcontacts.add(x.getNcontacts().doubleValue());
			});
			dataset.addSeries("Nº de contactes", listcontacts.stream().mapToDouble(Double::doubleValue).toArray(), 20);
			JFreeChart chart = ChartFactory.createHistogram("Número de Contactes per Pacient", "Número de Contactes",
					"Número de Pacients", dataset);
			ChartPanel cp = new ChartPanel(chart);
			frame.getContentPane().add(cp);
		}
	}

	// Method that generates symptoms statistics graphs.
	private void generateStatsSintoms(JFrame frame, String token) {
		List<Stats> stats = ApiConnector.getStats(token);
		if (stats != null && stats.size() > 0) {
			HistogramDataset dataset = new HistogramDataset();
			List<Double> listsintoms = new ArrayList<Double>();
			stats.forEach(x -> {
				listsintoms.add(x.getNsintoms().doubleValue());
			});
			dataset.addSeries("Nº de símptomes", listsintoms.stream().mapToDouble(Double::doubleValue).toArray(), 20);
			JFreeChart chart = ChartFactory.createHistogram("Número de Símptomes per Pacient", "Número de Símptomes",
					"Número de Pacients", dataset);
			ChartPanel cp = new ChartPanel(chart);
			frame.getContentPane().add(cp);
		}
	}

	private Container getContentPane() {
		return null;
	}

	private void setTitle(String string) {

	}

	public Window getFrame() {
		return frame;
	}

	public JButton getFrequencyButton() {
		return frequencyButton;
	}

	public void setFrequencyButton(JButton frequencyButton) {
		this.frequencyButton = frequencyButton;
	}

	public JButton getStatsButton() {
		return statsButton;
	}

	public void setStatsButton(JButton statsButton) {
		this.statsButton = statsButton;
	}

	public JButton getStatsPButton() {
		return statsPButton;
	}

	public void setStatsPButton(JButton statsPButton) {
		this.statsPButton = statsPButton;
	}

	private static XYDataset createDataset() {

		DefaultXYDataset ds = new DefaultXYDataset();

		double[][] data = { { 0.1, 0.2, 0.3 }, { 1, 2, 3 } };

		ds.addSeries("series1", data);

		return ds;
	}

	public void setSize(int i, int j) {
		// TODO Esbozo de método generado automáticamente

	}
}
