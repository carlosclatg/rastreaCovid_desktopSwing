package com.ioc.rastreacovid.communication;

import com.google.gson.Gson;
import com.ioc.rastreacovid.mappers.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

//Class to manage the API connection//
public class ApiConnector {

	private static final String URL = "http://localhost:8080/api";
	private static final String URL_auth = URL + "/user/auth";
	private static final String URL_getAllPatients = URL + "/pacients";
	private static final String URL_getPatientById = URL + "/pacient/";
	private static final String URL_postPatient = URL + "/pacient/";
	private static final String URL_getSintoms = URL + "/sintoms/";
	private static final String URL_deletePatient = URL + "/pacient/";
	private static final String URL_getFrequency = URL + "/stats-freq-sin/";
	private static final String URL_getStats = URL + "/stats";
	private static final String URL_getUsers = URL + "/users/";
	private static final String URL_postUser = URL + "/user/";
	private static final String URL_deleteUser = URL + "/user/";

	private static final String LANG_CAT = "cat";
	private static final String LANG_ES = "es";
	private static final String LANG_ENG = "eng";

	// In this method we check and validate that the access is correct with the user
	// and password, we obtain the token
	public static String authenticate(String email, String pass) {

		String token;

		try {
			/**
			 * HttpClient.newBuilder().
			 * https://stackoverflow.com/questions/50025086/in-java-what-is-the-simplest-way-to-create-an-sslcontext-with-just-a-pem-file
			 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/javax/net/ssl/SSLContext.html
			 */
			BodyAuthenticate body = new BodyAuthenticate();
			body.setEmail(email);
			body.setPassword(pass);
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_auth))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(body))).build();

			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				Token p = g.fromJson(response.body(), Token.class);
				System.out.println("The token is " + p.getToken());
				return p.getToken();
			case (400):
				System.out.println(response.body());
				return null;
			case (500):
				System.out.println("server error:" + response.body());
				return null;
			}

		} catch (Exception e) {
			System.out.println("There was an error");
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}

	// Method to obtain the patients that are in the DB.
	public static List<Patient> getAllPatients(String token) {
		try {
			System.out.println(URL_getAllPatients);
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_getAllPatients))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token).GET()
					.build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(response.statusCode());

			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				Patient[] p = g.fromJson(response.body(), Patient[].class);
				return Arrays.asList(p);
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	// Method to obtain the users that are in the DB.
	public static List<User> getUsers(String token) {
		try {
			System.out.println(URL_getUsers);
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_getUsers))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token).GET()
					.build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(response.statusCode());

			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				User[] p = g.fromJson(response.body(), User[].class);
				return Arrays.asList(p);
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	// Method to obtain the details of the patients that are in the DB.
	public static PatientDetail getPacientById(String token, String id) {
		try {

			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_getPatientById + id))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token).GET()
					.build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(response.statusCode());
			System.out.println(response.body());
			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				return g.fromJson(response.body(), PatientDetail.class);
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	// Method for the creation of users in the application.
	public static Id createUser(String token, UserPost userpost) {
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_postUser))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token)
					.POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(userpost))).build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(response.statusCode());
			System.out.println(response.body());
			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				return g.fromJson(response.body(), Id.class);
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static String deleteUser(String token, String email) { //
		try {

			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_deleteUser + email))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token).DELETE()
					.build();

			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());
			System.out.println(response.statusCode());
			System.out.println(response.body());
			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				String ret = g.fromJson(response.body(), String.class); // This will return an ok, when it has been
																		// removed.
				return ret;
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	// Method for the creation of patients in the application.
	public static Id postPacient(String token, PatientPost patientPost) {
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_postPatient))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token)
					.POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(patientPost))).build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(response.statusCode());
			System.out.println(response.body());
			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				return g.fromJson(response.body(), Id.class);
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		/**
		 * Ejemplo de creación de PatientPost: PatientPost pp = new PatientPost();
		 * pp.setName("Carlos"); pp.setSurname("Calvo"); pp.setBirthDate(1000000);
		 * pp.setPhone(890765123); pp.setPCRDate(10000000); List<String> sin = new
		 * ArrayList<String>(); sin.add("60684ba4c609df2b79a8879c"); //los obtienes de
		 * hacer un getsintoms sin.add("606b104c0216041e56299365");
		 * sin.add("60684c70c609df2b79a887a0"); pp.setSintoms(sin); pp.setContacts(new
		 * ArrayList<ContactWithoutId>()); List<ContactWithoutId> contacts = new
		 * ArrayList<ContactWithoutId>(); contacts.add(new ContactWithoutId("Donald",
		 * "Trump", 666888111)); pp.setContacts(contacts);
		 * ApiConnector.postPacient(token, pp);
		 */
	}

	// ** TODO NEXT SPRINT ** //

	// lang can be cat, eng or es depending on the language of the app, by default
	// cat.
	public static List<Sintom> getAllSintoms(String token, String lang) {
		try {
			if (lang == null)
				lang = LANG_CAT;
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_getSintoms + lang))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token).GET()
					.build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(response.statusCode());
			System.out.println(response.body());
			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				return Arrays.asList(g.fromJson(response.body(), Sintom[].class));
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// ** TODO NEXT SPRINT ** //

	// Method for delete of patients in the application.
	public static String deletePatient(String token, String pacientId) { // pacientid is the _id
		try {

			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_deletePatient + pacientId))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token).DELETE()
					.build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(response.statusCode());
			System.out.println(response.body());
			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				return g.fromJson(response.body(), String.class); // This will return an ok, when it has been removed.
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static List<CountSintom> getFrequency(String token, String lang) { // lang puede ser cat, eng o es
																				// dependiendo del idioma de la app. Por
																				// defecto cat.
		try {
			if (lang == null)
				lang = LANG_CAT;
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_getFrequency + lang))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token).GET()
					.build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(response.statusCode());
			System.out.println(response.body());
			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				return Arrays.asList(g.fromJson(response.body(), CountSintom[].class));
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static List<Stats> getStats(String token) { // lang puede ser cat, eng o es dependiendo del idioma de la app.
														// Por defecto cat.
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_getStats))
					.header("Content-Type", "application/json").setHeader("Authorization", "Bearer " + token).GET()
					.build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(response.statusCode());
			System.out.println(response.body());
			switch (response.statusCode()) {
			case (200):
				Gson g = new Gson();
				return Arrays.asList(g.fromJson(response.body(), Stats[].class));
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
