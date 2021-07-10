package com.pariksha.controller;

import com.pariksha.model.Role;
import com.pariksha.model.User;
import com.pariksha.model.UserRole;
import com.pariksha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");

        // encooding password bcryptpassword encoder
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(2L);
        role.setRoleName("normal");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user, roles);
    }

    // get user by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) throws Exception {
        return this.userService.getUser(username);
    }

    // delete user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) throws Exception {
        this.userService.deleteUser(userId);
    }

    //update the record
    @PutMapping("/{userId}")
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody User user) throws Exception {
        this.userService.updateUser(userId, user);
    }

}
