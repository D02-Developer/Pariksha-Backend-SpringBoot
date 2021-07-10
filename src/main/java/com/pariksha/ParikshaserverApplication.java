package com.pariksha;

import com.pariksha.model.Role;
import com.pariksha.model.User;
import com.pariksha.model.UserRole;
import com.pariksha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ParikshaserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {


		SpringApplication.run(ParikshaserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Code..!");

//		User user = new User();
//		user.setFirstname("Dhairya");
//		user.setLastname("Mehta");
//		user.setUsername("dhairya02");
//		user.setPassword(this.bCryptPasswordEncoder.encode("123"));
//		user.setPhone("7016433780");
//		user.setEmail("dhairya@gmail.com");
//		user.setProfile("default.png");
//
//		Role role1 = new Role();
//		role1.setRoleId(1L);
//		role1.setRoleName("admin");
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//
//		userRoleSet.add(userRole);
//
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());


	}
}
