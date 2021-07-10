package com.pariksha.service;

import com.pariksha.model.User;
import com.pariksha.model.UserRole;

import java.util.Set;

public interface UserService {

    // creating user
    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    // get user by username
    User getUser(String username) throws Exception;

    // delete user by id
    void deleteUser(Long userId) throws Exception;

    // update user by id
    void updateUser(Long userId, User user) throws Exception;
}
