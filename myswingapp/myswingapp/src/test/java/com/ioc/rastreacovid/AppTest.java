package com.ioc.rastreacovid;

import static org.junit.Assert.assertTrue;

import javax.swing.JPanel;

import com.ioc.rastreacovid.screens.DashboardScreen;
import com.ioc.rastreacovid.screens.LoginForm;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void loginTestOK()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(400,200);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("Abcd1234");
        frame.getSUBMIT().doClick();
    }

    @Test   
    public void loginTestKOemail()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(400,200);
        frame.setVisible(true);
        frame.getText1().setText("testfalse@gmail.com");
        frame.getText2().setText("Abcd1234");
        frame.getSUBMIT().doClick();
    }

    @Test 
    public void loginTestKOpassword()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(400,200);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("12345");
        frame.getSUBMIT().doClick();
    }

    @Test 
    public void loginTestKO()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(400,200);
        frame.setVisible(true);
        frame.getText1().setText("testfalse@gmail.com");
        frame.getText2().setText("1234");
        frame.getSUBMIT().doClick();
    }

    @Test 
    public void loginTestNullEmail()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(400,200);
        frame.setVisible(true);
        frame.getText1().setText("");
        frame.getText2().setText("12345");
        frame.getSUBMIT().doClick();
    }

    @Test 
    public void loginTestNullPassword()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(400,200);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("");
        frame.getSUBMIT().doClick();
    }

    @Test   
    public void logoutOK()
    {
        LoginForm frame = new LoginForm();
        frame.setSize(400,200);
        frame.setVisible(true);
        frame.getText1().setText("m.rocio.b.f@gmail.com");
        frame.getText2().setText("Abcd1234");
        frame.getSUBMIT().doClick();
        frame.getToken();

        DashboardScreen frame2 = new DashboardScreen();
        frame2.setSize(800, 200);
        frame2.setVisible(true);
        frame2.getLogoutButton().doClick();      
    }
}


    

