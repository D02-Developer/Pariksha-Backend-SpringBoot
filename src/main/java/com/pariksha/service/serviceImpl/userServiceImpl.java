package com.pariksha.service.serviceImpl;

import com.pariksha.model.User;
import com.pariksha.model.UserRole;
import com.pariksha.repository.RoleRepo;
import com.pariksha.repository.UserRepo;
import com.pariksha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class userServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    // creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepo.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is already created..!!");
            throw new Exception("User is already created..!!");
        }
        else {
            // user create
            for (UserRole ur: userRoles) {
                roleRepo.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepo.save(user);

        }


        return null;
    }


    // get user by username
    @Override
    public User getUser(String username) throws Exception {
        return this.userRepo.findByUsername(username);
    }

    // delete user by id
    @Override
    public void deleteUser(Long userId) throws Exception {
        this.userRepo.deleteById(userId);
    }

    // update user by id
    @Override
    public void updateUser(Long userId, User user) throws Exception {

        User user1 = this.userRepo.findById(userId).get();

        user1.setUsername(user.getUsername());
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setPassword(user.getPassword());
        user1.setProfile(user.getProfile());

        this.userRepo.save(user1);
    }

}
