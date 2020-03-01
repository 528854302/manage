package com.a528854302.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static String username="admin";
    private static String password="$2a$10$D3TdM2TFq.I.POb2h6H8DuGj1rWdVwi9.aZ/EeOT3SKQGVyGJU7ga";


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s.equals(username)){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority("user");
            grantedAuthorities.add(grantedAuthority);
            return new User(username,password,grantedAuthorities);
        }
        else {
            return null;
        }
    }
}
