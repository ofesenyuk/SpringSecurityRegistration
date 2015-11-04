/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.entities;

/**
 *
 * @author sf
 */
public class AnnouncementSearchCriteria {
    
    private String category;
    private String author;

    final public String ALL_CATEGORIES = "all";
    final public String MY = "my";
    final public String ALL_AUTHORS = "all";
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
