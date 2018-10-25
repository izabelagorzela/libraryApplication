The application delivers the functionality of public library support for readers. 
It requires a MySQL database that you can create by executing the sql script in the library_copy.sql file (default configuration with root user).

To run the application, proceed as follows:
1) start the server
2) start the client (desktop application)

For now this part can be run from IDE and jar files.
To start from jar build application using Maven with "mvn clean package" and start server with "java -jar server-1.0.jar" and client with "java -jar client-1.0.jar" command, respectively.