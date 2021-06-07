# rastreaCovid_desktop
#rastreaCovid

_Desktop version of the Rastreacovid application for user management.

# Structure:
_Folder structure:
_It has been divided into 4 main packages the "main", "communication", "mappers" and "screens".
_Main: we place the main class to be able to run the app.
_Communication: the class that handles the connection with the api.
_Mappers: the files needed to map all the information we need to obtain and display.
_Screens: the classes with the design and logic of the screens.

On the other side we have another folder where we have the AppTest.java, with the unit tests done.

# Definition of classes:
_Communication/ApiConnector.java: class to manage the connection with the API.

_Mappers/BodyAuthenticate.java: class that maps the correct user access, checking email and password, generation of getters and setters.
_Mappers/Contact.java: class that maps the information we have about the contact, generation of getters and setters.
_Mappers/Patient.java: class that maps the information we have about the patient, generation of getters and setters.
_Mappers/PatientDetail.java: class that maps the information we have about the patient detail, generation of getters and setters.
_Mappers/Symptom.java: class that maps the information we have about the symptoms, generation of getters and setters.
_Mappers/Token.java: mapping of the token, generation of getters and setters.
_Mappers/ContactWithoutId.java: In this class we start all the data we need from the contacts without ID, so that we can later display them with information, generation of getters and setters.
_Mappers/CountSintom.java: class that helps us to obtain the symptoms, generation of getters and setters.
_Mappers/DeleteUser.java: class to be able to remove users from our application.
_Mappers/Id.java: generation of getters and setters in order to obtain the id.
_Mappers/Stats.java: Class that helps us to generate statistics and generation of getters & setters.
_Mappers/UpdatePatient.java: Class that we use to be able to update a patient, with the getter and setters.
_Mappers/UpdateUser.java: Class that we use to be able to update a user, with the getters and setters automatic.
_Mappers/User.java: We set and get information user and then display them and generation of getters & setters.
_Mappers/UserPost.java: Class to obtain the user data and to be able to do post, and the getters and setters automatic.


_Screens/DashboardScreen.java: class the contains the window design and the logic of the main screen once the user logs in, has different options, such as check patients, update patients, delete patients, check users, delete users, view statistics of symptoms and patients, a COVID information button and logout.
_Screens/LoginForm.java: class that contains the window design and the logic of the login screen so that the user can indicate user and password.
_Screens/PatienDetailsScreen.java: class containing the window layout and logic for displaying patient details, such as date of birth, date of PCR, symptoms or contacts associated with that patient.
_Screens/PatientScreen.java: class that contains the window design and the logic to display a list of all the patients discharged in the system.
_Screens/CreateUserScreen.java: class that contains the design and the logic to create a new user in our application.
_Screens/DeletePatientScreen.java: class that contains the design and the logic to delete a patient from our application.
_Screens/DeleteUserScreen.java: class that contains the design and the logic to delete a user from our application.
_Screens/InfoScreen.java: in this class contains the logic to display information about the covid through links that open in the browser.
_Screens/StatisticsScreen.java: in this class is the logic and design of some statistic graphs about the patients and the symptoms we have registered in our application.
_Screens/UpdatePatientScreen.java: class used to update the patient data we have in our application. (In this version it does not work correctly.)
_Screens/UpdateUserScreen.java: lass used to update the user data we have in our application.
_Screens/UserScreen.java: class that contains the window design and the logic to display a list of all the users discharged in the system.

# Run:
_From the folder containing the project:
_1. Browse for the "App.java" file.
_2. Run

# Manual Test:
_From the folder containing the project:
_1. Browse for the "App.java" file.
_2. Run
_3. You have to enter an e-mail address and a password. The system will validate that both are correct.
    If it is correct, another window will appear informing you of the token and with the possibility to log out.
    If you click on "log out" you will return to the previous window.
    If the data entered is not correct, you will get an alert informing you that it is incorrect.

# Automated unit tests:
_From the folder containing the project:
_1. Browse for the "AppTest.java" file.
_2. You can test the complete file or by method.
_3. Each method checks a different use case.

# Developed with
_Eclipse

# Version
_RastreaCovid 3.0
_RastreaCovid 2.0
_RastreaCovid

# Author
_Rocio Bernabé
