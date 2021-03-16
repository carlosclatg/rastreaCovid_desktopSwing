package com.ioc.rastreacovid;

import com.ioc.rastreacovid.screens.LoginForm;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        System.out.println( "Hello World!" );
        LoginForm frame = new LoginForm();
        frame.setSize(400,200);
        frame.setVisible(true);

    }
}
