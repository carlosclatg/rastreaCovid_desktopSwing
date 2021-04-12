package com.ioc.rastreacovid;

import java.util.prefs.Preferences;

import com.ioc.rastreacovid.screens.DashboardScreen;
import com.ioc.rastreacovid.screens.LoginForm;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        Preferences prefs = Preferences.userNodeForPackage(LoginForm.class);
        String token = prefs.get("token", "0");
        if (token == "0" || token.equals("token")) {
            LoginForm frame = new LoginForm();
            frame.setSize(500, 500);
            frame.setVisible(true);
        } else {
            DashboardScreen frame = new DashboardScreen();
            frame.setSize(500, 500);
            frame.setVisible(true);
        }
    }
}
