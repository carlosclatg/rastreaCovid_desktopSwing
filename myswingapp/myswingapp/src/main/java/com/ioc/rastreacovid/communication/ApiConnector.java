package com.ioc.rastreacovid.communication;

import com.google.gson.Gson;
import com.ioc.rastreacovid.mappers.*;

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
    private static final String URL_postPatient = URL + "/pacient";
    private static final String URL_getSintoms =  URL + "/sintoms/";
    private static final String URL_deletePatient =  URL + "/pacient/";
    private static final String URL_getFrequency =  URL + "/stats-freq-sin/";
    private static final String URL_getStats =  URL + "/stats";


    private static final String LANG_CAT = "cat";
    private static final String LANG_ES = "es";
    private static final String LANG_ENG = "eng";


    public static String authenticate(String email, String pass) {

        String token;

        try{
            /**
             *             HttpClient.newBuilder().
             * https://stackoverflow.com/questions/50025086/in-java-what-is-the-simplest-way-to-create-an-sslcontext-with-just-a-pem-file
             * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/javax/net/ssl/SSLContext.html
             */
            BodyAuthenticate body = new BodyAuthenticate();
            body.setEmail(email);
            body.setPassword(pass);
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

    public static PatientDetail getPacientById(String token, String id){
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
                    return g.fromJson(response.body(), PatientDetail.class);
                default:
                    return null;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static Id postPacient(String token, PatientPost patientPost){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL_postPatient))
                    .header("Content-Type", "application/json")
                    .setHeader("Authorization", "Bearer " + token)
                    .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(patientPost)))
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
                    return g.fromJson(response.body(), Id.class);
                default:
                    return null;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        /**Ejemplo de creación de PatientPost:
         *                 PatientPost pp =  new PatientPost();
         *                 pp.setName("Carlos");
         *                 pp.setSurname("Calvo");
         *                 pp.setBirthDate(1000000);
         *                 pp.setPhone(890765123);
         *                 pp.setPCRDate(10000000);
         *                 List<String> sin = new ArrayList<String>();
         *                 sin.add("60684ba4c609df2b79a8879c"); //los obtienes de hacer un getsintoms
         *                 sin.add("606b104c0216041e56299365");
         *                 sin.add("60684c70c609df2b79a887a0");
         *                 pp.setSintoms(sin);
         *                 pp.setContacts(new ArrayList<ContactWithoutId>());
         *                 List<ContactWithoutId> contacts =  new ArrayList<ContactWithoutId>();
         *                 contacts.add(new ContactWithoutId("Donald", "Trump", 666888111));
         *                 pp.setContacts(contacts);
         *                 ApiConnector.postPacient(token, pp);
         */
    }

    public static List<Sintom> getAllSintoms(String token, String lang){ //lang puede ser cat, eng o es dependiendo del idioma de la app. Por defecto cat.
        try{
            if(lang == null) lang = LANG_CAT;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL_getSintoms + lang))
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
                    return Arrays.asList(g.fromJson(response.body(), Sintom[].class));
                default:
                    return null;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public static String deletePatient (String token, String pacientId){ //pacientid es el _id
        try{

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL_deletePatient + pacientId ))
                    .header("Content-Type", "application/json")
                    .setHeader("Authorization", "Bearer " + token)
                    .DELETE()
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
                    return g.fromJson(response.body(), String.class); //Esto devuelve simplemente un ok.
                default:
                    return null;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }


    public static List<CountSintom> getFrequency(String token, String lang){ //lang puede ser cat, eng o es dependiendo del idioma de la app. Por defecto cat.
        try{
            if(lang == null) lang = LANG_CAT;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL_getFrequency + lang))
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
                    return Arrays.asList(g.fromJson(response.body(), CountSintom[].class));
                default:
                    return null;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Stats> getStats(String token){ //lang puede ser cat, eng o es dependiendo del idioma de la app. Por defecto cat.
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL_getStats))
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
                    return Arrays.asList(g.fromJson(response.body(), Stats[].class));
                default:
                    return null;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
