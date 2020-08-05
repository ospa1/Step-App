# Step-App
Android App that counts the users steps.
the user can login or sign up to save progress on the database but it is optional.
the app begins to count steps while the application is in the foreground and stops if it goes to the background or is closed.
when the app calls the onPause method the app will store the steps locally using sqlite and in the cloud using firebase.

##How to run:
open the folder in android studio as a project.
run the project on an android phone since it is needed to be able to use the sensor.
