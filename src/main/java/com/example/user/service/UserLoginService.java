package com.example.user.service;

import com.example.user.model.UserLogin;
import com.example.user.repository.UserLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    UserLoginRepo userLoginRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin user= userLoginRepo.findByUserName(username);
        if(user==null)
            throw new UsernameNotFoundException("User 404");
        return new UserPrincipal(user);    }
}
