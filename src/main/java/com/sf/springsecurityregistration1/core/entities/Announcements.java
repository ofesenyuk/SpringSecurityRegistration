/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.TimeZone;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sf
 */
@Entity
@Table(name = "announcements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Announcements.findAll", query = "SELECT a FROM Announcements a"),
    @NamedQuery(name = "Announcements.findById", query = "SELECT a FROM Announcements a WHERE a.id = :id"),
    @NamedQuery(name = "Announcements.findByUsername", query = "SELECT a FROM Announcements a WHERE a.username = :username"),
    @NamedQuery(name = "Announcements.findByPublicationDate", query = "SELECT a FROM Announcements a WHERE a.publicationDate = :publicationDate"),
    @NamedQuery(name = "Announcements.findByHeader", query = "SELECT a FROM Announcements a WHERE a.header = :header"),
    @NamedQuery(name = "Announcements.findByTitle", query = "SELECT a FROM Announcements a WHERE a.title = :title"),
    @NamedQuery(name = "Announcements.findByContent", query = "SELECT a FROM Announcements a WHERE a.content = :content")})
public class Announcements implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publication_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp publicationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "header")
    private String header;
    @Transient
    private String newHeader;
    @Basic(optional = false)
    @NotNull
    @Size(min = 10, max = 30)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 20, max = 400)
    @Column(name = "content")
    private String content;

    public Announcements() {
    }

    public Announcements(Integer id) {
        this.id = id;
    }

    public Announcements(Integer id, String username, Timestamp publicationDate, String header, String title, String content) {
        this.id = id;
        this.username = username;
        this.publicationDate = publicationDate;
        this.header = header;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPublicationDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("Ukraine/Kiev"));
        return dateFormat.format(publicationDate.getTime());
    }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Announcements)) {
            return false;
        }
        Announcements other = (Announcements) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sf.springsecurityregistration1.core.entities.Announcements[ id=" + id + " ]";
    }

    public String getNewHeader() {
        return newHeader;
    }

    public void setNewHeader(String newHeader) {
        this.newHeader = newHeader;
    }
    
}
