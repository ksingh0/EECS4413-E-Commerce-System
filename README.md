INSTALLATION INSTRUCTIONS

Step 1: Import Maven Project
Download the .zip file
Go to the location of the downloaded .zip file and unzip the file.
Open eclipse.
Select File >> Import >> (Import wizard opens) >> Maven >> Existing Maven Projects
Click next.
Where it says “root directory”, click browse and browse to where you unzipped the file. Select the folder and click select folder.
Check the checkbox beside the project, which will now have appeared under “Projects”.
Click finish.

Step 2: Set up Tomcat Server
Make sure you are in Java EE Perspective
If you’re not, in the top toolbar select Window >> Perspective >> Open Perspective >> Other… >> Java EE >> Open
Go to the Servers tab
Click the link to create a new server. A wizard will open, select Apache >> Tomcat v10.1 Server.
Click next.
If it asks you to, navigate to where your Tomcat installation files are (for where it says “Tomcat installation directory”). (Commonly “Program Files/Apache Software Foundation/Tomcat 10.1”).
Press Finish.
In the project explorer in eclipse, go to Servers >> Tomcat >> server.xml and in the design, change “port” from -1 to a valid port.

Step 3: Run on Tomcat Server.
In Eclipse, go to Run >> Run Configurations >> Your Tomcat Server >> Arguments find the Working directory box, click on the “Other” radio button, then click Workspace, then apply. (Needed to properly access local databases)
In the project explorer, right click on the imported project and go to Run As >> Run on Server.
Select the Tomcat server created in step 2.
Click next.
Select the project under “available” and move it to “configured” (click add).
Click finish.
The project will run.

