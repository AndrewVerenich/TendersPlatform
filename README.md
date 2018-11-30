# TendersPlatform
This web application holds tenders for building projects. Users of the application are project organizations and customers. Customers create tenders in the application. Project organizations leave their offers. The application then determines the winner of the tender.
## Getting Started
Clone this repo to your local machine using:
```bash
https://github.com/AndrewVerenich/TendersPlatform
```
## Application deployment
Create new user in your MySQL 5.7 and create new schema:
```bash
mysql
CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON * . * TO 'root'@'localhost';
FLUSH PRIVILEGES;
CREATE SCHEMA `tenders_db` DEFAULT CHARACTER SET utf8 ;
```
Add a corresponding server block to your Maven settings.xml in %MAVEN_PATH%/conf/settings.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings>
    <servers>
        <server>
            <id>TomcatServer</id>
            <username>yourTomcatUsername</username>
            <password>yourTomcatPassword</password>
        </server>
    </servers>
</settings>
```
The application is running on the Tomcat 8.0.52 server.
Add an user with roles manager-gui and manager-script in %TOMCAT8_PATH%/conf/tomcat-users.xml:
```xml
<?xml version='1.0' encoding='utf-8'?>
<tomcat-users>
    <role rolename="manager-gui"/>
    <role rolename="manager-script"/>
    <user username="yourTomcatUsername" password="yourTomcatPassword" roles="manager-gui,manager-script" />
</tomcat-users>
```
Run your Tomcat server.

Deploy WAR with Maven on your Tomcat Server use the following command.
```bash
mvn tomcat7:deploy
```
Run the application.
```bash
http://localhost:8080/
```
## Technology stack
+ Spring Framework
+ Hibernate
+ Maven
+ JSP
+ Bootstrap

## License
[MIT](https://choosealicense.com/licenses/mit/)
