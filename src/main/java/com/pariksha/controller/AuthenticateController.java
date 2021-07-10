package com.pariksha.controller;

import com.pariksha.config.JwtUtil;
import com.pariksha.model.JwtReq;
import com.pariksha.model.JwtRes;
import com.pariksha.model.User;
import com.pariksha.service.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // generate token

    @PostMapping("/generate-token")
    public ResponseEntity generateToken(@RequestBody JwtReq jwtReq) throws Exception {

        try {

            authenticate(jwtReq.getUsername(), jwtReq.getPassword());
        }
        catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User Not Found..!!");
        }

        // User Authenticate thai gayu 6

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtReq.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtRes(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        }
        catch (DisabledException e) {
            throw new Exception("User Disabled..!!" +e.getMessage());
        }
        catch (BadCredentialsException e) {
            throw new Exception("Invalid Credential..!!" +e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
