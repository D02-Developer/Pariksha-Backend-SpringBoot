package com.pariksha.repository;

import com.pariksha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    public User findByUsername(String username);

}
