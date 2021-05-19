package com.ioc.rastreacovid;

import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.ioc.rastreacovid.communication.ApiConnector;
import com.ioc.rastreacovid.mappers.Id;
import com.ioc.rastreacovid.mappers.PatientDetail;
import com.ioc.rastreacovid.mappers.User;
import com.ioc.rastreacovid.mappers.UserPost;
import com.ioc.rastreacovid.screens.CreateUserScreen;
import com.ioc.rastreacovid.screens.DashboardScreen;
import com.ioc.rastreacovid.screens.DeletePatientScreen;
import com.ioc.rastreacovid.screens.DeleteUserScreen;
import com.ioc.rastreacovid.screens.InfoScreen;
import com.ioc.rastreacovid.screens.LoginForm;
import com.ioc.rastreacovid.screens.PatientDetailsScreen;
import com.ioc.rastreacovid.screens.PatientScreen;
import com.ioc.rastreacovid.screens.PatientsScreen;
import com.ioc.rastreacovid.screens.StatisticsScreen;
import com.ioc.rastreacovid.screens.UpdateUserScreen;
import com.ioc.rastreacovid.screens.UserScreen;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 * 
	 */

	// Unit tests Sprint 1 - TEA2

	@Test
	public void loginTestOK() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
	}

	@Test
	public void loginTestKOemail() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("testfalse@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
	}

	@Test
	public void loginTestKOpassword() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("12345");
		frame.getSUBMIT().doClick();
	}

	@Test
	public void loginTestKO() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("testfalse@gmail.com");
		frame.getText2().setText("12345");
		frame.getSUBMIT().doClick();
	}

	@Test
	public void loginTestNullEmail() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("");
		frame.getText2().setText("12345");
		frame.getSUBMIT().doClick();
	}

	@Test
	public void loginTestNullPassword() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("");
		frame.getSUBMIT().doClick();
	}

	@Test
	public void logoutOK() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getLogoutButton().doClick();
		frame.setVisible(true);
	}

	// Unit tests Sprint 2 - TEA3

	@Test
	public void clickPacients() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getPacientsButton().doClick();

		PatientScreen patient = new PatientScreen();
		patient.setSize(1050, 500);
		patient.setVisible(true);

	}

	@Test
	public void doubleClickInPacients() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashboard = new DashboardScreen();
		dashboard.setSize(500, 500);
		dashboard.setVisible(true);
		dashboard.getPacientsButton().doClick();

		PatientScreen patient = new PatientScreen();
		patient.setSize(1050, 500);
		patient.setVisible(true);
		JTable table = patient.getTable();
		PatientDetail detail = ApiConnector.getPacientById(token, (String) table.getValueAt(0, 0));
		PatientDetailsScreen pds = new PatientDetailsScreen(detail);
		pds.getFrame().setVisible(true);
	}

//Unit tests Sprint 3 - TEA4

	@Test
	public void createNewUser() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getCreateUserButton().doClick();
		CreateUserScreen createUser = new CreateUserScreen();
		createUser.setVisible(true);
		createUser.getTname().setText("Ioc");
		createUser.getTsurname().setText("Junit");
		createUser.getTemail().setText("ioc@junit.com");
		createUser.getTtlf().setText("651282898");
		createUser.getTpass().setText("RocioTest01");
		createUser.getTpassc().setText("RocioTest01");
		createUser.getSub().doClick();

	}
	@Test
	public void createNewUserWithoutPass() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getCreateUserButton().doClick();
		CreateUserScreen createUser = new CreateUserScreen();
		createUser.setVisible(true);
		createUser.getTname().setText("Ioc2");
		createUser.getTsurname().setText("Junit");
		createUser.getTemail().setText("ioc@junit.com");
		createUser.getTtlf().setText("651282898");
		createUser.getTpassc().setText("RocioTest01");
		createUser.getSub().doClick();
	}
	
	@Test
	public void createNewUserAlreadyExists() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getCreateUserButton().doClick();
		CreateUserScreen createUser = new CreateUserScreen();
		createUser.setVisible(true);
		createUser.getTname().setText("Rocio");
		createUser.getTsurname().setText("Bernabé");
		createUser.getTemail().setText("m.rocio.b.f@gmail.com");
		createUser.getTtlf().setText("651282898");
		createUser.getTpass().setText("RocioTest01");
		createUser.getTpassc().setText("RocioTest01");
		createUser.getSub().doClick();
	}
	
	@Test
	public void createNewUserAndReset() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getCreateUserButton().doClick();
		
		CreateUserScreen createUser = new CreateUserScreen();
		createUser.setVisible(true);
		createUser.getTname().setText("Ioc");
		createUser.getTsurname().setText("Junit");
		createUser.getTemail().setText("ioc@junit.com");
		createUser.getTtlf().setText("651282898");
		createUser.getTpass().setText("RocioTest01");
		createUser.getTpassc().setText("RocioTest01");
		createUser.getReset().doClick();

	}
	
// Rebajar el 5 de la línea 240 si da fuera de rango
	@Test
	public void deleteUser() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getDeleteUserButton().doClick();
		DeleteUserScreen deleteUser = new DeleteUserScreen();
		deleteUser.setVisible(true);

		JTable table = deleteUser.getTable();
		String id = (String) table.getValueAt(6, 0);

		String delete = ApiConnector.deleteUser(token, id);
		assertTrue(delete.equalsIgnoreCase("ok"));
//    if (delete.equalsIgnoreCase("ok")) {
//		JOptionPane.showMessageDialog(null, "L'usuari s'ha eliminat correctament");
//
//	}

	}

	@Test
	public void doubleClickUserUpdate() {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getUsuarisButton().doClick();

		UserScreen userScreen = new UserScreen();
		userScreen.setSize(1050, 500);
		userScreen.setVisible(true);
		JTable table = userScreen.getTable();
		Vector fileVector = userScreen.getFileVector();

		Vector userVector = (Vector) fileVector.get(4);
		User user = new User();
		user.set_Id(userVector.get(0).toString());
		user.setName(userVector.get(1).toString());
		user.setSurname(userVector.get(2).toString());
		user.setEmail(userVector.get(3).toString());
		user.setType(userVector.get(4).toString());
		user.setPhone((Integer) userVector.get(5));
		UpdateUserScreen uus = new UpdateUserScreen(user);
		uus.getFrame().setVisible(true);
		uus.getTname().setText((uus.getTname().getText() + "JunitTest"));
		uus.getTpass().setText("Abcd1234");
		uus.getTpassc().setText("Abcd1234");
		uus.getSub().doClick();
	}

	@Test
	public void searchUserName() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getUsuarisButton().doClick();

		UserScreen userScreen = new UserScreen();
		userScreen.setSize(1050, 500);
		userScreen.setVisible(true);
		userScreen.getSearchName().setText("Admin");
		userScreen.getSearchButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void searchUserEmail() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getUsuarisButton().doClick();

		UserScreen userScreen = new UserScreen();
		userScreen.setSize(1050, 500);
		userScreen.setVisible(true);
		userScreen.getSearchEmail().setText("m.rocio.b.f@gmail.com");
		userScreen.getSearchButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void searchUserEmailAndName() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getUsuarisButton().doClick();

		UserScreen userScreen = new UserScreen();
		userScreen.setSize(1050, 500);
		userScreen.setVisible(true);
		userScreen.getSearchEmail().setText("rastreador@ioc.com");
		userScreen.getSearchName().setText("Rocio");
		userScreen.getSearchButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void searchPacientsName() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getPacientsButton().doClick();

		PatientsScreen patientsScreen = new PatientsScreen();
		patientsScreen.setSize(1050, 500);
		patientsScreen.setVisible(true);
		patientsScreen.getSearchName().setText("Rocio");
		patientsScreen.getSearchButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void searchPacientsTlf() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getPacientsButton().doClick();

		PatientsScreen patientsScreen = new PatientsScreen();
		patientsScreen.setSize(1050, 500);
		patientsScreen.setVisible(true);
		patientsScreen.getSearchPhone().setText("977112233");
		patientsScreen.getSearchButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void searchPacientsNameAndSurname() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getPacientsButton().doClick();

		PatientsScreen patientsScreen = new PatientsScreen();
		patientsScreen.setSize(1050, 500);
		patientsScreen.setVisible(true);
		patientsScreen.getSearchName().setText("John");
		patientsScreen.getSearchSurname().setText("Doe");
		patientsScreen.getSearchButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void deletePatient() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getdeletePatientButton().doClick();
		DeletePatientScreen deletePatient = new DeletePatientScreen();
		deletePatient.setVisible(true);
		JTable table = deletePatient.getTable();
		String patientid = (String) table.getValueAt(15, 0);
		String delete = ApiConnector.deletePatient(token, patientid);
		// assertTrue(delete.equalsIgnoreCase("ok"));
		if (delete.equalsIgnoreCase("ok")) {
			JOptionPane.showMessageDialog(null, "L'usuari s'ha eliminat correctament");

		}

	}

	@Test
	public void statisticsFreqSym() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getStatisticsButton().doClick();

		StatisticsScreen statis = new StatisticsScreen();
		statis.getFrequencyButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void statisticsStatiSym() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getStatisticsButton().doClick();

		StatisticsScreen statis = new StatisticsScreen();
		statis.getStatsButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void statisticsStatiPat() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getStatisticsButton().doClick();

		StatisticsScreen statis = new StatisticsScreen();
		statis.getStatsPButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void infoScreenWhat() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getInfoButton().doClick();

		InfoScreen info = new InfoScreen();
		info.getWhatButton().doClick();
		info.getCloseButton().doClick();
		Thread.sleep(5000);

	}

	@Test
	public void infoScreenSym() throws InterruptedException {
		LoginForm frame = new LoginForm();
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getText1().setText("m.rocio.b.f@gmail.com");
		frame.getText2().setText("Abcd1234");
		frame.getSUBMIT().doClick();
		String token = frame.getToken();

		DashboardScreen dashbboard = new DashboardScreen();
		dashbboard.setSize(500, 500);
		dashbboard.setVisible(true);
		dashbboard.getInfoButton().doClick();

		InfoScreen info = new InfoScreen();
		info.getSymButton().doClick();
		info.getCloseButton().doClick();
		Thread.sleep(5000);
	}

}
