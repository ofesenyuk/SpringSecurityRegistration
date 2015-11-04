/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.repository;

import com.sf.springsecurityregistration1.core.entities.UserRoles;
import com.sf.springsecurityregistration1.core.entities.Users;

/**
 *
 * @author sf
 */
public interface UserRepository {

    public Users save(Users accountDto);

    public UserRoles saveRole(UserRoles roleDto);

    public Users findByUserName(String userName);
    
}
