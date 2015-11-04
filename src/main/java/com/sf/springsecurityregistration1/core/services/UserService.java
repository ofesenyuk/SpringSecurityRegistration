/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.services;

import com.sf.springsecurityregistration1.core.entities.UserRoles;
import com.sf.springsecurityregistration1.core.entities.Users;
import com.sf.springsecurityregistration1.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sf
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public Users registerNewUserAccount(Users accountDto) 
            throws UserExistsException {
        if (userExist(accountDto.getUsername())) {
            throw new UserExistsException(
                    "There is an account with that user name:"
                            + accountDto.getUsername());
        }
        UserRoles role = new UserRoles();
        role.setRole("ROLE_USER");
        role.setUsername(accountDto.getUsername());
        repository.saveRole(role);
        accountDto.setEnabled((short)1);
        return repository.save(accountDto);
//        return new Users("mkyong", "123456", (short)0);
    }

    private boolean userExist(String userName) {
        Users user = repository.findByUserName(userName);
        return user != null;
//        return false;
    }
    
    public static String buildHtmlEntityCode(String input) {
        StringBuilder output = new StringBuilder(input.length() * 2);

        int len = input.length();
        int code, code1, code2, code3, code4;
        char ch;

        for (int i = 0; i < len;) {
            code1 = input.codePointAt(i);
            if (code1 >> 3 == 30) {
                code2 = input.codePointAt(i + 1);
                code3 = input.codePointAt(i + 2);
                code4 = input.codePointAt(i + 3);
                code = ((code1 & 7) << 18) | ((code2 & 63) << 12) 
                        | ((code3 & 63) << 6) | (code4 & 63);
                i += 4;
                output.append("&#" + code + ";");
            } else if (code1 >> 4 == 14) {
                code2 = input.codePointAt(i + 1);
                code3 = input.codePointAt(i + 2);
                code = ((code1 & 15) << 12) | ((code2 & 63) << 6) 
                        | (code3 & 63);
                i += 3;
                output.append("&#" + code + ";");
            } else if (code1 >> 5 == 6) {
                code2 = input.codePointAt(i + 1);
                code = ((code1 & 31) << 6) | (code2 & 63);
                i += 2;
                output.append("&#" + code + ";");
            } else {
                code = code1;
                i += 1;

                ch = (char) code;
                if (ch >= 'a' && ch <= 'z' || ch >= 'A' 
                        && ch <= 'Z' || ch >= '0' && ch <= '9') {
                    output.append(ch);
                } else {
                    output.append("&#" + code + ";");
                }
            }
        }

        return output.toString();
    }

}
