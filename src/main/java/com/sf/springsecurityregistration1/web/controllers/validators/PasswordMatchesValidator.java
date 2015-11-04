/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sf.springsecurityregistration1.web.controllers.validators;

import com.sf.springsecurityregistration1.core.entities.Users;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author sf
 */
public class PasswordMatchesValidator 
    implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Users user = (Users) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
    
}
