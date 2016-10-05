# CRUD-Services
Requirements for the project to run:

Eclipse Mars
Tomcat 8
Maven 2
JDK 1.8
TestFile format used : JSON

Tomcat is installed and configured with manager-script and manager-GUI user roles assigned.
Settings.xml must contain the Tomcat server credentials.

Files to be changed for this project:
POM.xml - build -> plugins -> plugin -> configuration -> server - Use the name used in the above step for this field.

Build and deploy Server Steps:
1) Extract the submitted file to your workspace.
2) Start the Tomcat server by running the command sudo $CATALINA_HOME/bin/startup.sh command. 
3) Navigate to the directory where POM.xml is located.
4) Package the project and deploy it to the Tomcat server by running the command 'mvn tomcat7:deploy'.
5) You can go to the manager profile of Tomcat server to test this.(Entering the url 'http://localhost:8080' in the web browser).

Running the client and Testing:
1) Import the project to eclipse as a Maven project.
2) Run Gradebook_UI.java in edu.asu.cse564.CRUD_Gradebook_Client.

Methods Implemented:
CREATE: createStudent and createGradeItem
READ: getStudent and getGradebook
UPDATE: updateGrade for a particular student
DELETE: deleteStudent, deleteGradeItem and delete Grade for a particular student


