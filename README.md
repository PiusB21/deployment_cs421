# deployment_cs421

The Springboot application provides endpoints: /subject and /student to perform CRUD operations on data from a Postgres database  

The hosted endpoints are:

-to get the list of students : http://16.171.35.83:8080/api/v1/student/all 

-to get the list of subjects : http://16.171.35.83:8080/api/v1/subject/all

other endpoints for creation,updation and deletion are included in the springboot app controller 


Using AWS cloud services, I launched a t3 micro instance with Ubuntu OS,
making all necessary installation including Java's Jdk and postgres database system.
Cloned the project from Github, set it up as well as the database, and built it using maven.


I created and run a service for the application and, installed and configured an NGINX server to allow remote access to the springboot application.



