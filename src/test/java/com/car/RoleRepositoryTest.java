package com.car;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.car.category.repository.ResponseRepository;
import com.car.category.repository.StatusRepository;
import com.car.entites.Response;
import com.car.entites.Role;
import com.car.entites.Status;
import com.car.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	ResponseRepository responseRepository;

	@Test
	public void testCreateRoles() {
		Role user = new Role("ADMIN");
		Role users = new Role("USER");
		roleRepository.saveAll(Arrays.asList(user,users));
		List<Role> listRoles = roleRepository.findAll();
		assertThat(listRoles.size()).isEqualTo(2);		
	}
	
	
	@Test
	public void testCreateStues() {
		Status user = new Status("ACTIVATE");
		Status users = new Status("DEACTIVATE");
		statusRepository.saveAll(Arrays.asList(user,users));
		List<Status> listStatus= statusRepository.findAll();
		assertThat(listStatus.size()).isEqualTo(2);		
	}
	
	@Test
	public void testBidding() {
		Response response1 = new Response("PENDING");
		Response response2 = new Response("ACCEPTED");
		Response response3 = new Response("DENY");
		responseRepository.saveAll(Arrays.asList(response1,response2,response3));
		List<Response> listResponse = responseRepository.findAll();
		assertThat(listResponse.size()).isEqualTo(3);		
	}
}
