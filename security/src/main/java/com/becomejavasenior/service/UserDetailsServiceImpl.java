package com.becomejavasenior.service;

import com.becomejavasenior.*;
import com.becomejavasenior.config.ConfigDao;
import com.becomejavasenior.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

//@Service
//@Import(ConfigSecurity.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            User user = userService.getUserByEmail(s);
            if (user == null) {
                throw new UsernameNotFoundException("Oops!");
            }
            Set<GrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority(user.getRole().getName()));
            userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), roles);
        } catch (ServiceException e) {
        }
        return userDetails;
    }
}
