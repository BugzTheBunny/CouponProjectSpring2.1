package com.slava.proj.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.slava.proj.project.models.Customer;
import com.slava.proj.project.models.User;
import com.slava.proj.project.repo.CustomerRepo;
import com.slava.proj.project.repo.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	@Autowired
	private CustomerRepo reposit;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repo.findByUsername(username);
		Customer customer = reposit.findByUsername(username);
		if (user == null) {
			return new CustomerPrincipal(customer);
		}
		return new UserPrincipal(user);
	}

}
