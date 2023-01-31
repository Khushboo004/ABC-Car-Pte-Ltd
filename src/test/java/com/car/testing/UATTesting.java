package com.car.testing;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.car.entites.User;
import com.car.repository.RoleRepository;
import com.car.repository.UserReopsitory;
import com.car.service.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UATTesting {
	
	@Autowired
	UserReopsitory userReopsitory;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	
	
	@Test
	public void TestCreateUser() {
		User user = new User();
		try {
			user.setName("khusboo");
			user.setEmail("khushboo@gmail.com");
			user.setPassword("khushboo");
			user.setPhone("01858707176");
			user.setEnable(true);
			when(userReopsitory.save(user)).thenReturn(user);
			userService.save(user);
			verify(userReopsitory).save(any(User.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
