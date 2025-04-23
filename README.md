# deployment_cs421

The Springboot application provides endpoints: /subject and /student to perform CRUD operations on data from a Postgres database  

The hosted endpoints are:

-to get the list of students : http://16.16.207.108:8080/api/v1/student/all 

-to get the list of subjects : http://16.16.207.108:8080/api/v1/subject/all

other endpoints for creation,updation and deletion are included in the springboot app controller 


Using AWS cloud services, I launched a t3 micro instance with Ubuntu OS,
making all necessary installation including Java's Jdk and postgres database system.
Cloned the project from Github, set it up as well as the database, and built it using maven.


I created and run a service for the application and, installed and configured an NGINX server to allow remote access to the springboot application.


The following scripts have been included in the bash_scripts directory:

health_check.sh
monitors the operational health of the server and deployed API. It collects system resource usage statistics, including CPU load, memory usage, and available disk space. Additionally, it verifies that the web server (e.g., Nginx or the Spring Boot service) is running and functional. It also tests the availability of key API endpoints (/student/all and /subject/all) by issuing curl requests to confirm they return a successful HTTP 200 OK response. All results are recorded with timestamps in /var/log/server_health.log. Any failures, such as low disk space or unresponsive endpoints, are logged as warnings to aid in proactive maintenance.

backup_api.sh
responsible for creating regular backups of both the API project files and the associated database. It archives the deployment directory (e.g., /opt/deployment_task) into a timestamped .tar.gz file and exports the database contents into a .sql dump file. Both backup files are stored in /home/ubuntu/backups/. To manage storage usage effectively, the script automatically deletes backup files older than 7 days. This ensures data can be quickly restored in the event of system failure or data corruption.

update_server.sh
automates the update process for both the server environment and the API application. It begins by updating the Ubuntu package list and upgrading installed packages. Next, it navigates to the specified local Git repository and attempts to pull the latest changes from the remote source. If the pull is successful, the script restarts the API service (e.g., deployment_task) to apply the updates. The entire process is logged with timestamps in /var/log/update.log. If the Git pull fails, the script logs the error and exits gracefully without restarting the application, thus preventing deployment of incomplete or broken updates.


Backup Schemes Concepts were explored below:

A backup scheme is a plan for creating, maintaining, and managing data backups, ensuring their availability for recovery.

1. Full Backup
How it's executed: Copies all data from the system all the time

Advantages:
    - Quick restore time
    - Easy storage management
    - Easy version control

Disadvantages:
    - Demands the most storage space
    - Takes a long time to back up files depending on the size
    - Higher risk of data loss since all the data is stored in one place

2. Incremental Backup
How it's executed: Backs up data has changed since the last backup activity

Advantages:
    - Efficient use of storage space

Disadvantages:
    - Slower to restore since data must be pieced together from multiple backups

3. Differential Backup
How it's executed: backs up all changes made since the last full backup

Advantages:
    - Faster to restore than incremental backup
    - Takes less space than full backup

Disadvantages:
    - Potential for failed recovery if any of the backup sets are incomplete
    - Backup takes longer and requires more storage space than incremental backup
    - Slow and complex to restore compared to full backups


