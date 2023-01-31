package com.car.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.car.entites.User;
import com.car.repository.UserReopsitory;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserReopsitory userReopsitory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userReopsitory.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Could not find User");
		}
		return new MyUserDetails(user);
	}
}
