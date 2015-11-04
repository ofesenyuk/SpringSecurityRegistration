/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.services;

/**
 *
 * @author sf
 */
public class UserExistsException extends Exception {

    UserExistsException(String message) {
        super(message);
    }
    
}
