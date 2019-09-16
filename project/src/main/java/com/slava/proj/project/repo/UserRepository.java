package com.slava.proj.project.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slava.proj.project.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findById(long id);

	Collection<User> findAllByRole(String role);
}
