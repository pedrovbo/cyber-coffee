package com.gft.cybercoffee.service;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.cybercoffee.model.CyberCoffeeUser;
import com.gft.cybercoffee.repository.CyberCoffeeUserRepository;

@Service
public class CyberCoffeeUserDetailsService implements UserDetailsService {

    @Autowired
    private CyberCoffeeUserRepository cyberCoffeeUserRepository;

    @Autowired
    private BCryptPasswordEncoder enconder;


    public CyberCoffeeUser findByLogin(String username){        
        return cyberCoffeeUserRepository.findByUsername(username);        
    }

    @Override    
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CyberCoffeeUser cyberCoffeeUser = cyberCoffeeUserRepository.findByUsername(username);
        return new User(cyberCoffeeUser.getUsername(), cyberCoffeeUser.getPassword(), Set.of(cyberCoffeeUser.getAuthorities()));
    }

    @Transactional
    public CyberCoffeeUser salvarUsuario(@Valid CyberCoffeeUser cyberCoffeeUser) {
        cyberCoffeeUser.setPassword(enconder.encode(cyberCoffeeUser.getPassword())); 
        cyberCoffeeUser.setAuthorities(new SimpleGrantedAuthority("USER"));
        return cyberCoffeeUserRepository.save(cyberCoffeeUser);
    }

    

}
