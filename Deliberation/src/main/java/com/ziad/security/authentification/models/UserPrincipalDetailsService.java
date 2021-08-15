package com.ziad.security.authentification.models;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ziad.models.User;
import com.ziad.repositories.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.getUserByUsername(username);
        if(user==null) {
        	throw new UsernameNotFoundException("User not found");
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
