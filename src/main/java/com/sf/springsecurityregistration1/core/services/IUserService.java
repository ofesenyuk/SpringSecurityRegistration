/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.services;

import com.sf.springsecurityregistration1.core.entities.Users;

/**
 *
 * @author sf
 */
public interface IUserService {
    Users registerNewUserAccount(Users accountDto) throws UserExistsException;
}
