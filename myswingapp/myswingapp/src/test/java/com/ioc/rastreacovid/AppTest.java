package com.ioc.rastreacovid;

import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.PatientDetail;
import com.ioc.rastreacovid.screens.DashboardScreen;
import com.ioc.rastreacovid.screens.LoginForm;
import com.ioc.rastreacovid.screens.PatientDetailsScreen;
import com.ioc.rastreacovid.screens.PatientScreen;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * 
     */
	
	//Unit tests Sprint 1 - TEA2
	
    @Test
    public void loginTestOK()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("Abcd1234");
        frame.getSUBMIT().doClick();
    }

    @Test   
    public void loginTestKOemail()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getText1().setText("testfalse@gmail.com");
        frame.getText2().setText("Abcd1234");
        frame.getSUBMIT().doClick();
    }

    @Test 
    public void loginTestKOpassword()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("12345");
        frame.getSUBMIT().doClick();
    }

    @Test 
    public void loginTestKO()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getText1().setText("testfalse@gmail.com");
        frame.getText2().setText("12345");
        frame.getSUBMIT().doClick();
    }

    @Test 
    public void loginTestNullEmail()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getText1().setText("");
        frame.getText2().setText("12345");
        frame.getSUBMIT().doClick();
    }

    @Test 
    public void loginTestNullPassword()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("");
        frame.getSUBMIT().doClick();
    }

    @Test   
    public void logoutOK()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("Abcd1234");
        frame.getSUBMIT().doClick();
        frame.getToken();

        DashboardScreen frame2 = new DashboardScreen();
        frame2.setSize(500, 500);
        frame2.setVisible(true);
        frame2.getLogoutButton().doClick();
        frame.setVisible(true);        
    }

	// Unit tests Sprint 2 - TEA3

    @Test   
    public void clickPacients()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("Abcd1234");
        frame.getSUBMIT().doClick();
        frame.getToken();
        
        
        DashboardScreen frame2 = new DashboardScreen();
        frame2.setSize(500, 500);
        frame2.setVisible(true);
        //frame.setVisible(true); 
        frame2.getPacientsButton().doClick();
        
        PatientScreen frame3 = new PatientScreen();
        frame3.setSize(1050, 500);
        frame3.setVisible(true);
        //frame.setVisible(true);  
    }
   
    @Test   
    public void doubleClickInPacients()
    {
    	LoginForm frame = new LoginForm();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("Abcd1234");
        frame.getSUBMIT().doClick();
        String token = frame.getToken();
        
        DashboardScreen frame2 = new DashboardScreen();
        frame2.setSize(500, 500);
        frame2.setVisible(true);
        frame2.getPacientsButton().doClick();
        
        PatientScreen frame3 = new PatientScreen();
        frame3.setSize(1050, 500);
        frame3.setVisible(true);
        JTable table = frame3.getTable();
        PatientDetail detail = ApiConnector.getPacientById(token, (String) table.getValueAt(table.getSelectedRow(), 0));
		PatientDetailsScreen pds = new PatientDetailsScreen(detail);
		pds.getFrame().setVisible(true);
    }
}
    

