# rastreaCovid_desktop
#rastreaCovid

_Desktop version of the Rastreacovid application for user management.

# Structure:
_Folder structure:
_It has been divided into 4 main packages the "main", "communication", "mappers" and "screens".
_1. Main: we place the main class to be able to run the app.
_2. Communication: the class that handles the connection with the api.
_3. Mappers: the files needed to map all the information we need to obtain and display.
_4. Screens: the classes with the design and logic of the screens.

On the other side we have another folder where we have the AppTest.java, with the unit tests done.

# Definition of classes:
_Communication/ApiConnector.java: class to manage the connection with the API.

_Mappers/BodyAuthenticate.java: class that maps the correct user access, checking email and password, generation of getters and setters.
_Mappers/Contact.java: class that maps the information we have about the contact, generation of getters and setters.
_Mappers/Patient.java: class that maps the information we have about the patient, generation of getters and setters.
_Mappers/PatientDetail.java: class that maps the information we have about the patient detail, generation of getters and setters.
_Mappers/Symptom.java: class that maps the information we have about the symptoms, generation of getters and setters.
_Mappers/Token.java: mapping of the token, generation of getters and setters.

_Screens/DashboardScreen.java: class that contains the window design and the logic of the main screen once the user logs in, it has different options, such as checking patients or logging out.
_Screens/LoginForm.java: class that contains the window design and the logic of the login screen so that the user can indicate user and password.
_Screens/PatienDetailsScreen.java: class containing the window layout and logic for displaying patient details, such as date of birth, date of PCR, symptoms or contacts associated with that patient.
_Screens/PatientScreen.java: class that contains the window design and the logic to display a list of all the patients discharged in the system.

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
_RastreaCovid 2.0
_RastreaCovid

# Author
_Rocio Bernabé
