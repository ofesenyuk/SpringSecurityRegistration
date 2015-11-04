/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.repository;

import com.sf.springsecurityregistration1.core.entities.AnnouncementSearchCriteria;
import com.sf.springsecurityregistration1.core.entities.Announcements;
import com.sf.springsecurityregistration1.core.entities.Category;
import com.sf.springsecurityregistration1.core.services.UserService;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sf
 */
public class AnnouncementRepositoryJDBC implements AnnouncementRepository {

    private static final String AUTHOR = "username";
    private static final String CATEGORY = "header";
    
    private String driverClassName;
    private String dbURL;
    private String dbUsername;
    private String dbPassword;

    @Override
    public Collection<Category> findAllCategories() {
        Set<Category> foundCategories = new HashSet<>();
        try {
            System.out.println("findAllCategories ");
            Announcements a = new Announcements();
            String table = a.getClass()
                .getAnnotation(javax.persistence.Table.class).name();
            final String FIND_CATEGORIES = "SELECT * FROM " + table;
            System.out.println(FIND_CATEGORIES);
            Connection connection = null;
            try {
                Properties connectionProps = new Properties();
                connectionProps.put("user", dbUsername);
                connectionProps.put("password", dbPassword);
                connection = DriverManager.getConnection(dbURL,
                        connectionProps);
                Statement findCategoriesStmnt = connection.createStatement();
                ResultSet rs = findCategoriesStmnt
                        .executeQuery(FIND_CATEGORIES);
                while (rs.next()) {
                    Category category = new Category(rs.getString(CATEGORY));
                    System.out.println(category.getValue() + " " 
                            + category.getHtmlValue());
                    foundCategories.add(category);
//                    foundAuthors.add(UserService
//                            .buildHtmlEntityCode(rs.getString(AUTHOR)));
                    
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (Exception e) {
            System.out.println("findAllCategories: " + e.getMessage());
        }
        return foundCategories;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    
    @Override
    public Collection<Announcements> 
        findAnnouncements(AnnouncementSearchCriteria criteria) {
        List<Announcements> foundAnnouncements = new ArrayList<>();
        try {
            System.out.println("findAnnouncements ");
            Announcements announcement = new Announcements();
            Map<String,String> columns = returnTableFields(announcement); //new HashMap<>();
//            System.out.println("announcement " + Arrays.toString(announcement.getClass().getDeclaredField("id").getAnnotations()));
            String table = announcement.getClass()
                    .getAnnotation(javax.persistence.Table.class).name();
            final String FIND_ANNOUNCEMENTS = "SELECT * FROM " + table;
            Connection connection = null;
            try {
                Properties connectionProps = new Properties();
                connectionProps.put("user", dbUsername);
                connectionProps.put("password", dbPassword);
                connection = DriverManager.getConnection(dbURL,
                        connectionProps);
                Statement foundAnnouncementsStmnt = connection.createStatement();
                ResultSet rs = foundAnnouncementsStmnt
                        .executeQuery(FIND_ANNOUNCEMENTS);
                while (rs.next()) {
//                    Timestamp timestamp = rs.getTimestamp(columns.get("publicationDate"));
//                    System.out.println("timestamp " + timestamp);
                    announcement 
                            = new Announcements(rs.getInt(columns.get("id")), 
                                    rs.getString(columns.get("username")), 
                                    rs.getTimestamp(columns.get("publicationDate")), 
                                    rs.getString(columns.get("header")), 
                                    rs.getString(columns.get("title")), 
                                    rs.getString(columns.get("content")));
                    String authorToSelect = criteria.getAuthor();
                    System.out.println("authorToSelect " + authorToSelect 
                        + " cmp " + announcement.getUsername());
                    if (authorToSelect != null 
                            && !authorToSelect.equals(criteria.ALL_AUTHORS)
                            && !announcement.getUsername()
                                    .contains(authorToSelect)
                            && !authorToSelect.contains(announcement
                                         .getUsername())) {
                        continue;
                    }
                    String headerToSelect = criteria.getCategory();
                    if (headerToSelect != null 
                            && !headerToSelect.equals(criteria.ALL_CATEGORIES)
                            && !announcement.getHeader()
                                    .equals(headerToSelect)) {
                        System.out.println("headerToSelect " + headerToSelect
                                + " cmp " + announcement.getHeader());
                        continue;
                    }
                    foundAnnouncements.add(announcement);
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (Exception e) {
            System.out.println("findByUserName: " + e.getMessage());
        }
        return foundAnnouncements;
    }

    @Override
    public Collection<String> findAllAuthors() {
        Set<String> foundAuthors = new HashSet<>();
        try {
            System.out.println("findAllAuthors ");
            Announcements a = new Announcements();
            String table = a.getClass()
                    .getAnnotation(javax.persistence.Table.class).name();
            final String FIND_AUTHORS = "SELECT * FROM " + table;
            Connection connection = null;
            try {
                Properties connectionProps = new Properties();
                connectionProps.put("user", dbUsername);
                connectionProps.put("password", dbPassword);
                connection = DriverManager.getConnection(dbURL,
                        connectionProps);
                Statement findCategoriesStmnt = connection.createStatement();
                ResultSet rs = findCategoriesStmnt
                        .executeQuery(FIND_AUTHORS);
                while (rs.next()) {
                    String author = rs.getString(AUTHOR);
                    System.out.println("author " + author);
                    foundAuthors.add(author);
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (Exception e) {
            System.out.println("findAllAuthors: " + e.getMessage());
        }
        return foundAuthors;
    }

    @Override
    public Announcements findAnnouncement(long announcementId) {
        Announcements foundAnnouncement = new Announcements();
        try {
            System.out.println("findAnnouncement ");
            Map<String, String> columns = returnTableFields(foundAnnouncement);
            String table = foundAnnouncement.getClass()
                    .getAnnotation(javax.persistence.Table.class).name();
            final String FIND_ANNOUNCEMENT = "SELECT * FROM " + table 
                    + " WHERE id = " + announcementId;
            System.out.println(FIND_ANNOUNCEMENT);
            Connection connection = null;
            try {
                Properties connectionProps = new Properties();
                connectionProps.put("user", dbUsername);
                connectionProps.put("password", dbPassword);
                connection = DriverManager.getConnection(dbURL,
                        connectionProps);
                Statement findAnnouncementStmnt = connection.createStatement();
                ResultSet rs = findAnnouncementStmnt
                        .executeQuery(FIND_ANNOUNCEMENT);
                while (rs.next()) {
                    foundAnnouncement = new Announcements((int)announcementId,
                            rs.getString(columns.get("username")),
                            rs.getTimestamp(columns.get("publicationDate")),
                            rs.getString(columns.get("header")),
                            rs.getString(columns.get("title")),
                            rs.getString(columns.get("content")));
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (Exception e) {
            System.out.println("findAnnouncement: " + e.getMessage());
        }
        return foundAnnouncement;
    }

    private Map<String, String> returnTableFields(Announcements announcement) {
        Map<String, String> columns = new HashMap<>();
        Field[] fields = announcement.getClass().getDeclaredFields();
        final String COLUMN_MARKER = "javax.persistence.Column";
        final String NAME_MARKER = ", name=";
        for (Field field : fields) {
            String fieldName = field.getName();
            String annotationDescription = Arrays.toString(field
                    .getAnnotations());
            if (!annotationDescription.contains(COLUMN_MARKER)) {
                continue;
            }
            String columnDescription = annotationDescription
                    .split(COLUMN_MARKER)[1];
            if (!columnDescription.contains(NAME_MARKER)) {
                continue;
            }
            String columnName = columnDescription.split(NAME_MARKER)[1]
                    .split(",")[0];
//                System.out.println(columnName);
            columns.put(fieldName, columnName);
        }
        return columns;
    }

    @Override
    public Announcements save(Announcements announcement) {
        Map<String, String> columns = returnTableFields(announcement); 
        String table = announcement.getClass()
                .getAnnotation(javax.persistence.Table.class).name();
        final String ISERT_ANNONCEMENT = "INSERT INTO " + table
                + " (" + columns.get("username") + ", " 
                + columns.get("publicationDate") + ", " + columns.get("header")
                + ", " + columns.get("title") + ", " + columns.get("content")
                +") VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", dbUsername);
            connectionProps.put("password", dbPassword);
            connection = DriverManager.getConnection(dbURL, connectionProps);

            connection.setAutoCommit(false);
            PreparedStatement insertAnnouncement
                    = connection.prepareStatement(ISERT_ANNONCEMENT);
            insertAnnouncement.setString(1, announcement.getUsername());
            insertAnnouncement.setTimestamp(2, announcement
                    .getPublicationDate());
            insertAnnouncement.setString(3, announcement.getHeader());
            insertAnnouncement.setString(4, announcement.getTitle());
            insertAnnouncement.setString(5, announcement.getContent());
            insertAnnouncement.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AnnouncementRepositoryJDBC.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AnnouncementRepositoryJDBC.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return announcement;
    }

    @Override
    public Announcements update(Announcements announcement) {        
        Map<String, String> columns = returnTableFields(announcement);
        String table = announcement.getClass()
                .getAnnotation(javax.persistence.Table.class).name();
        final String UPDATE_ANNONCEMENT = "UPDATE " + table
                + " SET " + columns.get("header") +  " = ?, " 
                + columns.get("title") + " = ?, " 
                + columns.get("content") + " = ?" 
                + " WHERE " + columns.get("id") + " = ?";
        Connection connection = null;
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", dbUsername);
            connectionProps.put("password", dbPassword);
            connection = DriverManager.getConnection(dbURL, connectionProps);
            
            connection.setAutoCommit(false);
            PreparedStatement updateAnnouncement
                    = connection.prepareStatement(UPDATE_ANNONCEMENT);
            updateAnnouncement.setString(1, announcement.getHeader());
            updateAnnouncement.setString(2, announcement.getTitle());            
            updateAnnouncement.setString(3, announcement.getContent());
            updateAnnouncement.setInt(4, announcement.getId());
            updateAnnouncement.execute();
            connection.commit();
        } catch (SQLException ex) {
                Logger.getLogger(AnnouncementRepositoryJDBC.class.getName())
                        .log(Level.SEVERE, null, ex);
                return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AnnouncementRepositoryJDBC.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return announcement;
    }

    @Override
    public void delete(Announcements announcement) {
        String table = announcement.getClass()
                .getAnnotation(javax.persistence.Table.class).name();
        String DELETE_COMMAND = "DELETE FROM " + table + " WHERE ID = ?";
        Connection connection = null;
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", dbUsername);
            connectionProps.put("password", dbPassword);
            connection = DriverManager.getConnection(dbURL, connectionProps);

            connection.setAutoCommit(false);
            PreparedStatement updateAnnouncement
                    = connection.prepareStatement(DELETE_COMMAND);
            updateAnnouncement.setInt(1, announcement.getId());
            System.out.println("updateAnnouncement " + updateAnnouncement);
            updateAnnouncement.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AnnouncementRepositoryJDBC.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AnnouncementRepositoryJDBC.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
