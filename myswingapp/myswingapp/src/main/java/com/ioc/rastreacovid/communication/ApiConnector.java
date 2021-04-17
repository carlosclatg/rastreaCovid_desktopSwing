package com.ioc.rastreacovid.communication;

import com.google.gson.Gson;
import com.ioc.rastreacovid.mappers.BodyAuthenticate;
import com.ioc.rastreacovid.mappers.Patient;
import com.ioc.rastreacovid.mappers.PatientDetail;
import com.ioc.rastreacovid.mappers.Token;
import com.ioc.rastreacovid.screens.PatientDetailsScreen;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class ApiConnector {

    private static final String URL = "http://localhost:8080/api";
    private static final String URL_auth = URL + "/user/auth";
    private static final String URL_getAllPatients = URL + "/pacients";
    private static final String URL_getPatientById = URL + "/pacient/";


    public static String authenticate(String email, String pass) {

        String token;

        try{
            BodyAuthenticate body = new BodyAuthenticate();
            body.setEmail(email);
            body.setPassword(pass);
            System.out.println(new Gson().toJson(body).toString());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL_auth))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(body)))
                    .build();

            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()){
                case(200):
                    Gson g = new Gson();
                    Token p = g.fromJson(response.body(), Token.class);
                    System.out.println("The token is " + p.getToken());
                    return p.getToken();
                case(400):
                    System.out.println(response.body());
                    return null;
                case(500):
                    System.out.println("server error:" +response.body());
                    return null;
            }

        } catch (Exception e) {
            System.out.println("There was an error");
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public static List<Patient> getAllPatients(String token){
        try{
            System.out.println(URL_getAllPatients);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL_getAllPatients))
                    .header("Content-Type", "application/json")
                    .setHeader("Authorization", "Bearer " + token)
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());

            switch (response.statusCode()) {
                case (200):
                    Gson g = new Gson();
                    Patient[] p = g.fromJson(response.body(), Patient[].class);
                    return Arrays.asList(p);
                default:
                    return null;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }


    public static PatientDetail[] getPacientById(String token, String id){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL_getPatientById + id))
                    .header("Content-Type", "application/json")
                    .setHeader("Authorization", "Bearer " + token)
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());
            switch (response.statusCode()) {
                case (200):
                    Gson g = new Gson();
                	PatientDetailsScreen pds = new PatientDetailsScreen(g.fromJson(response.body(), PatientDetail[].class));
                	pds.getFrame().setVisible(true);
                    return g.fromJson(response.body(), PatientDetail[].class);
                default:
                    return null;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
