Before application run
     - create a database 'site' in MySQL and tables in it with queries written in SpringSecurityRegistration/src/main/resources/CreateTables.sql
     - database access attributes are read from SpringSecurityRegistration/src/main/webapp/WEB-INF/jdbc.properties (at present, they are jdbc.driverClassName=com.mysql.jdbc.Driver jdbc.url=jdbc:mysql://localhost:3306/site?useUnicode=true&characterEncoding=utf-8 jdbc.username=root jdbc.password=)
     - you can fill tables with test data launching SpringSecurityRegistration/src/test/java/com/sf/springsecurityregistration1/core/services/PersistTestDataInDB.java (it is the class with main method) 
The application was developed with Glassfish 4.0 server
