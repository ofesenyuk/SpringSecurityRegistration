/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.repository;

import com.sf.springsecurityregistration1.core.entities.UserRoles;
import com.sf.springsecurityregistration1.core.entities.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sf
 */
//@Repository("userRepository")
public class UserRepositoryJPA implements UserRepository {
    private String driverClassName;
    private String dbURL;
    private String dbUsername;
    private String dbPassword;
//    private final EntityManagerFactory factory;
//    UsersJpaController usersJpaController;
//
//    {
//        factory = Persistence
//                .createEntityManagerFactory("com"
//                        + ".sf_SpringSecurityRegistration1_war_1.0-SNAPSHOTPU");
//        UserTransaction userTransaction = 
//        usersJpaController = new UsersJpaController(null, factory);
//    }
    @PersistenceContext  
//(unitName = "com.sf_SpringSecurityRegistration1_war_1.0-SNAPSHOTPU")
    private EntityManager usersEntityManager; 
//            = factory.createEntityManager();

    @Override
//    @Transactional
    public Users save(Users accountDto) {
        try {
//            System.out.println("usersEntityManager.persist " 
//                    + accountDto.getUsername() + " " 
//                    + accountDto.getPassword());
//            usersEntityManager.getTransaction().begin();
//            usersEntityManager.persist(accountDto);
            persistWithJDBC(accountDto);
//            usersEntityManager.flush();
//            usersEntityManager.getTransaction().commit();
//            System.out.println("UserRepositoryJPA: " + accountDto.getUsername()
//                + " is persisted");
        } catch (Exception e) {
            System.out.println("UserRepositoryJPA: " 
                    + e.getMessage());
            return null;
        }
        return accountDto;
    }

    @Override
    public Users findByUserName(String userName) {
        try {
            System.out.println("findByUserName " + userName);
//            Users user = (Users) usersEntityManager
//                    .createNamedQuery("Users.findByUsername")
//                    .setParameter("username", userName).getSingleResult();
//            System.out.println("usersEntityManager " 
//                    + usersEntityManager.toString() + " " + user.getPassword());
            final String FIND_USER = "SELECT * FROM users WHERE username = '"
                    + userName + "'";
            Connection connection = null;
            String foundUserName = "";
            try {
                Properties connectionProps = new Properties();
                connectionProps.put("user", dbUsername);
                connectionProps.put("password", dbPassword);
                connection = DriverManager.getConnection(dbURL, 
                        connectionProps);
                Statement findUserStatement = connection.createStatement();
                ResultSet rs = findUserStatement.executeQuery(FIND_USER);
                while(rs.next()) {
                    foundUserName = rs.getString("username");
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
            if (foundUserName.isEmpty()) {
                return null;
            }
            Users user = new Users(foundUserName);
            return user;
        } catch (Exception e) {
            System.out.println("findByUserName: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public UserRoles saveRole(UserRoles roleDto) {
        try {            
//            usersEntityManager.getTransaction().begin();
//            usersEntityManager.persist(roleDto);
//            usersEntityManager.flush();
//            usersEntityManager.getTransaction().commit();
            persistWithJDBC(roleDto);
        } catch (Exception ex) {
            System.out.println("UserRepositoryJPA saveRole: " 
                    + ex.getMessage());
            return null;
        }
        return roleDto;
    }

    private <T> void persistWithJDBC(T dto) 
            throws SQLException {
        String table = dto.getClass()
                .getAnnotation(javax.persistence.Table.class).name();
//        System.out.println("table " + table);
        final String ROLE = "role";
        boolean isRole = table.toLowerCase().contains(ROLE);
        final String INSERT_USER = "INSERT INTO " + table 
                + " (username, " + (isRole ? ROLE : "password")
                +") VALUES (?, ?)";
        Connection connection = null;
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", dbUsername);
            connectionProps.put("password", dbPassword);
            connection = DriverManager.getConnection(dbURL, connectionProps);
            connection.setAutoCommit(false);
            PreparedStatement insertUser 
                    = connection.prepareStatement(INSERT_USER);
            if (isRole) {
                UserRoles role = (UserRoles) dto;
                insertUser.setString(1, role.getUsername());
                insertUser.setString(2, role.getRole());
            } else {
                Users user = (Users) dto;
                insertUser.setString(1, user.getUsername());
                insertUser.setString(2, user.getPassword());
            }
            insertUser.execute();
            connection.commit();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
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
}
