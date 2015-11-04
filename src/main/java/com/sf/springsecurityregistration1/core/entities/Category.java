/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.entities;

import com.sf.springsecurityregistration1.core.services.UserService;

/**
 *
 * @author sf
 */
public class Category {
    private String value;

    public Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getHtmlValue() {
        return UserService.buildHtmlEntityCode(value);
    }    
    
    public Integer getId() {
        return value.hashCode();
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Category other = (Category) obj;
        return value.equals(other.getValue());
    }
}
