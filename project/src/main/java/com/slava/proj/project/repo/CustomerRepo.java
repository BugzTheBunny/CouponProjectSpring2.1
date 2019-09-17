package com.slava.proj.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slava.proj.project.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

	Customer findByUsername(String username);

	Customer findById(long id);

}
