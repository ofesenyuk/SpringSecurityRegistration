/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sf
 */
public class PersistTestDataInDB {
    private static final String JDBC_URL_MARKER = "jdbc.url=";
    private static final String JDBC_USERNAME_MARKER = "jdbc.username=";
    private static final String JDBC_PASSWORD_MARKER = "jdbc.password=";
    
    public static String propertiesFileLocation 
            = "src/main/webapp/WEB-INF/jdbc.properties";
    public static String jdbcUrl;
    public static String jdbcUsername;
    public static String jdbcPassword;
    
    public static void main(String[] args) {
        PersistTestDataInDB instance = new PersistTestDataInDB();
        instance.readDBConnectionAttributes(propertiesFileLocation);
        instance.persistTestUsers(false);
        instance.persistTestUsers(true);
        instance.persistTestUserRoles();
        instance.persistTestData();
    }

    private void readDBConnectionAttributes(String propertiesFileLocation) {
        
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(propertiesFileLocation));
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains(JDBC_URL_MARKER)) {
                    jdbcUrl = sCurrentLine.split(JDBC_URL_MARKER)[1];
                    System.out.println(jdbcUrl);
                }
                if (sCurrentLine.contains(JDBC_USERNAME_MARKER)) {
                    jdbcUsername = sCurrentLine.split(JDBC_USERNAME_MARKER)[1];
                    System.out.println(jdbcUsername);
                }
                if (sCurrentLine.contains(JDBC_PASSWORD_MARKER)) {
                    jdbcPassword = sCurrentLine.split(JDBC_PASSWORD_MARKER)
                            .length > 0 
                            ? sCurrentLine.split(JDBC_PASSWORD_MARKER)[1] : "";
                    System.out.println(jdbcPassword);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void persistTestData() {
        Connection connection = null;
        final String INSERT_STATEMENT = "INSERT INTO announcements "
                + "(username, publication_date, header, title, content) "
                + " VALUES (?, ?, ?, ?, ?)";
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", jdbcUsername);
            connectionProps.put("password", jdbcPassword);
            connection = DriverManager.getConnection(jdbcUrl, connectionProps);

            connection.setAutoCommit(false);
            PreparedStatement insertAnnouncement
                    = connection.prepareStatement(INSERT_STATEMENT);
            insertAnnouncement.setString(1, "Pasha");
            insertAnnouncement.setTimestamp(2, 
                    new Timestamp((new Date()).getTime()));
            insertAnnouncement.setString(3, "продажа");
            insertAnnouncement.setString(4, "Ауди совсем новая");
            insertAnnouncement.setString(5, 
                    "Продам совсем новую Ауди 2015 года выпуска");
            insertAnnouncement.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PersistTestDataInDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("Insert 1: " + ex.getMessage());
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertAnnouncement
                    = connection.prepareStatement(INSERT_STATEMENT);
            insertAnnouncement.setString(1, "Masha");
            insertAnnouncement.setTimestamp(2, 
                    new Timestamp((new Date()).getTime()));
            insertAnnouncement.setString(3, "покупка");
            insertAnnouncement.setString(4, "Куплю куртку новую");
            insertAnnouncement.setString(5,
                    "Куплю куртку новую для себя размер 48");
            insertAnnouncement.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PersistTestDataInDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("Insert 2: " + ex.getMessage());
        }         
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertAnnouncement
                    = connection.prepareStatement(INSERT_STATEMENT);
            insertAnnouncement.setString(1, "Glasha");
            insertAnnouncement.setTimestamp(2, 
                    new Timestamp((new Date()).getTime()));
            insertAnnouncement.setString(3, "аренда");
            insertAnnouncement.setString(4, "Аренда дома для семьи");
            insertAnnouncement.setString(5,
                    "Сниму дом за городом для семьи из 8 человек");
            insertAnnouncement.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PersistTestDataInDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("Insert 3: " + ex.getMessage());
        }        
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertAnnouncement
                    = connection.prepareStatement(INSERT_STATEMENT);
            insertAnnouncement.setString(1, "Masha");
            insertAnnouncement.setTimestamp(2, 
                    new Timestamp((new Date()).getTime()));
            insertAnnouncement.setString(3, "услуги");
            insertAnnouncement.setString(4, "Помогу с трудоустройством в ИТ");
            insertAnnouncement.setString(5,
                    "Готовлю для собеседования в фирму \"ИТ технологии\"");
            insertAnnouncement.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PersistTestDataInDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("Insert 4: " + ex.getMessage());
        }        
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertAnnouncement
                    = connection.prepareStatement(INSERT_STATEMENT);
            insertAnnouncement.setString(1, "Pasha");
            insertAnnouncement.setTimestamp(2, 
                    new Timestamp((new Date()).getTime()));
            insertAnnouncement.setString(3, "знакомства");
            insertAnnouncement.setString(4, "Ищу огородников-проффесионалов");
            insertAnnouncement.setString(5, "Занимаюсь разведение сельдерея. "
                    + "Ищу огородников, занимающихся тем же.");
            insertAnnouncement.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PersistTestDataInDB.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("Insert 4: " + ex.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PersistTestDataInDB.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void persistTestUsers(boolean isTest) {
        Connection connection = null;
        final String INSERT_USER_STATEMENT = "INSERT INTO users "
                + "(username, password, enabled) "
                + " VALUES (?, ?, ?)";
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", jdbcUsername);
            connectionProps.put("password", jdbcPassword);
            connection = DriverManager.getConnection(jdbcUrl, connectionProps);

            connection.setAutoCommit(false);
            PreparedStatement insertUser
                    = connection.prepareStatement(INSERT_USER_STATEMENT);
            insertUser.setString(1, "Pasha");
            insertUser.setString(2, "123456");
            insertUser.setInt(3, 1);
            insertUser.execute();
            connection.commit();
        } catch (SQLException ex) {
            if (isTest) {
                System.out.println("Success! Duplicate usernames are not "
                        + "acceptable");
            } else {
                Logger.getLogger(PersistTestDataInDB.class.getName())
                    .log(Level.SEVERE, null, ex);
                System.out.println("Insert user 1: " + ex.getMessage());
            }
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertUser
                    = connection.prepareStatement(INSERT_USER_STATEMENT);
            insertUser.setString(1, "Masha");
            insertUser.setString(2, "123456");
            insertUser.setInt(3, 1);
            insertUser.execute();
            connection.commit();
        } catch (SQLException ex) {
            if (isTest) {
                System.out.println("Success! Duplicate usernames are not "
                        + "acceptable");
            } else {
                Logger.getLogger(PersistTestDataInDB.class.getName())
                    .log(Level.SEVERE, null, ex);
                System.out.println("Insert 2: " + ex.getMessage());
            }
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertUser
                    = connection.prepareStatement(INSERT_USER_STATEMENT);
            insertUser.setString(1, "Glasha");
            insertUser.setString(2, "123456");
            insertUser.setInt(3, 1);
            insertUser.execute();
            connection.commit();
        } catch (SQLException ex) {
            if (isTest) {
                System.out.println("Success! Duplicate usernames are not "
                        + "acceptable");
            } else {
                Logger.getLogger(PersistTestDataInDB.class.getName())
                    .log(Level.SEVERE, null, ex);
                System.out.println("Insert 3: " + ex.getMessage());
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PersistTestDataInDB.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void persistTestUserRoles() {
        Connection connection = null;
        final String INSERT_ROLE_STATEMENT = "INSERT INTO user_roles "
                + "(username, role) "
                + " VALUES (?, ?)";
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", jdbcUsername);
            connectionProps.put("password", jdbcPassword);
            connection = DriverManager.getConnection(jdbcUrl, connectionProps);

            connection.setAutoCommit(false);
            PreparedStatement insertUser
                    = connection.prepareStatement(INSERT_ROLE_STATEMENT);
            insertUser.setString(1, "Pasha");
            insertUser.setString(2, "ROLE_USER");
            connection.commit();
        } catch (SQLException ex) {
                Logger.getLogger(PersistTestDataInDB.class.getName())
                        .log(Level.SEVERE, null, ex);
                System.out.println("Insert user 1: " + ex.getMessage());
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertUser
                    = connection.prepareStatement(INSERT_ROLE_STATEMENT);
            insertUser.setString(1, "Masha");
            insertUser.setString(2, "ROLE_USER");
            insertUser.execute();
            connection.commit();
        } catch (SQLException ex) {
                Logger.getLogger(PersistTestDataInDB.class.getName())
                        .log(Level.SEVERE, null, ex);
                System.out.println("Insert 2: " + ex.getMessage());
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertUser
                    = connection.prepareStatement(INSERT_ROLE_STATEMENT);
            insertUser.setString(1, "Glasha");
            insertUser.setString(2, "ROLE_USER");
            insertUser.execute();
            connection.commit();
        } catch (SQLException ex) {
                Logger.getLogger(PersistTestDataInDB.class.getName())
                        .log(Level.SEVERE, null, ex);
                System.out.println("Insert 3: " + ex.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PersistTestDataInDB.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
