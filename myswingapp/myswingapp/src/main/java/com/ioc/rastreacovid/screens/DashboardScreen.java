package com.ioc.rastreacovid.screens;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.CountSintom;
import com.ioc.rastreacovid.mappers.Stats;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
public class DashboardScreen extends JFrame implements ActionListener, WindowListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
	public DashboardScreen() {
        Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
        String token = prefs.get("token", "token");
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(92, 255, 208));
        panel.setLayout(null);
        panel.setBounds(100, 100, 500, 500);
        getContentPane().add(panel, BorderLayout.CENTER);
        setTitle("Dashboard");
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(164, 217, 170, 70);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);
        
        JButton usuarisButton = new JButton("Usuaris");
        usuarisButton.setBounds(297, 76, 170, 70);
        panel.add(usuarisButton);
        
        JButton pacientsButton = new JButton("Pacients");
        pacientsButton.setBounds(29, 76, 170, 70);
        panel.add(pacientsButton);
        pacientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //PatientsScreen screen = new PatientsScreen();
                //screen.getFrame().setVisible(true);

                JFrame frame = new JFrame("Charts");

                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);


                //generatefrequencySintoms(frame, token);
                //generateStatsSintoms(frame, token);
                generateStatsPacients(frame, token);

            }
        });


        JLabel tokenLabel = new JLabel();
        tokenLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
        tokenLabel.setBounds(100, 321, 299, 34);
        tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tokenLabel.setText(token);
        panel.add(tokenLabel);
        
    }

    private void generatefrequencySintoms(JFrame frame, String token) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        System.out.println("g");
        List<CountSintom> countSintomList = ApiConnector.getFrequency(token, "cat");

        if(countSintomList != null && countSintomList.size() > 0){
            countSintomList.forEach(x ->{
                dataset.addValue(x.getCount(), "sintoma", x.get_id());
            });
        }

        JFreeChart chart = ChartFactory.createBarChart("Sintoms", "sintomas", "frequency", dataset);
        ChartPanel cp = new ChartPanel(chart);
        frame.getContentPane().add(cp);


    }

    private void generateStatsSintoms(JFrame frame, String token) {
        List<Stats> stats = ApiConnector.getStats(token);
        if(stats !=null && stats.size() > 0){
            HistogramDataset dataset = new HistogramDataset();
            List<Double> listsintoms = new ArrayList<Double>();
            stats.forEach(x-> {
                listsintoms.add(x.getNsintoms().doubleValue());
            });
            dataset.addSeries("number of sintoms", listsintoms.stream().mapToDouble(Double::doubleValue).toArray(), 20);
            JFreeChart chart = ChartFactory.createHistogram("Number of sintoms", "number of sintoms", "number of patients", dataset);
            ChartPanel cp = new ChartPanel(chart);
            frame.getContentPane().add(cp);
        }
    }

    private void generateStatsPacients(JFrame frame, String token) {
        List<Stats> stats = ApiConnector.getStats(token);
        if(stats !=null && stats.size() > 0){
            HistogramDataset dataset = new HistogramDataset();
            List<Double> listcontacts = new ArrayList<Double>();
            stats.forEach(x-> {
                listcontacts.add(x.getNcontacts().doubleValue());
            });
            dataset.addSeries("number of contacts", listcontacts.stream().mapToDouble(Double::doubleValue).toArray(), 20);
            JFreeChart chart = ChartFactory.createHistogram("Number of contacts", "number of contacts", "number of patients", dataset);
            ChartPanel cp = new ChartPanel(chart);
            frame.getContentPane().add(cp);
        }
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
        prefs.remove("token");
        LoginForm frame = new LoginForm();
        frame.setSize(500, 500);
        frame.setVisible(true);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        
    }
    
    public void actionPerformed1(ActionEvent e) {
    	UserScreen frame = new UserScreen();
    	frame.setSize(500, 500);
    	frame.setVisible(true);
    	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_ACTIVATED));
    	
    }
    

    public JButton getLogoutButton() {
        return getLogoutButton();
    }
    
    //TO DO
    public JButton getUsuarisButton() {
    	return getUsuarisButton();
    }
    
    //TO DO
    public JButton getpacientsButton() {
    	return getpacientsButton();
    	
    }

    public void setLogoutButton(JButton logoutButton) {
    }
    
  //TO DO
    public void setUsuarisbutton(JButton usuarisButton) {
    }
    
  //TO DO
    public void setpacientsButton(JButton pactientsButton) {
    }



    private static XYDataset createDataset() {

        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data = { {0.1, 0.2, 0.3}, {1, 2, 3} };

        ds.addSeries("series1", data);

        return ds;
    }
}

