/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.services;

import com.sf.springsecurityregistration1.core.entities.Users;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author sf
 */
@Service("userDetailsService")
//@Transactional
public class SecurityUserDetailsService implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private LoginAttemptService loginAttemptService;
//
//    @Autowired
//    private HttpServletRequest request;

    public SecurityUserDetailsService() {
        super();
    }

    // API
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
//        final String ip = request.getRemoteAddr();
//        if (loginAttemptService.isBlocked(ip)) {
//            throw new RuntimeException("blocked");
//        }
//
//        try {
//            final Users user = userRepository.findByEmail(email);
//            if (user == null) {
//                return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true, getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
//            }
//
//            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
//        } catch (final Exception e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    // UTIL
//    public final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
//        return getGrantedAuthorities(getPrivileges(roles));
//    }
//
//    private final List<String> getPrivileges(final Collection<Role> roles) {
//        final List<String> privileges = new ArrayList<String>();
//        final List<Privilege> collection = new ArrayList<Privilege>();
//        for (final Role role : roles) {
//            collection.addAll(role.getPrivileges());
//        }
//        for (final Privilege item : collection) {
//            privileges.add(item.getName());
//        }
//        return privileges;
//    }
//
//    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
//        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (final String privilege : privileges) {
//            authorities.add(new SimpleGrantedAuthority(privilege));
//        }
//        return authorities;
//    }

}
