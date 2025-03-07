package com.pariksha.service.serviceImpl;

import com.pariksha.model.User;
import com.pariksha.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepo.findByUsername(username);

        if(user == null) {
            System.out.println("User Not Found..!!");
            throw new UsernameNotFoundException("No User Found..!!");
        }
        return user;
    }
}
