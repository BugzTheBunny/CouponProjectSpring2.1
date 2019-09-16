package com.slava.proj.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slava.proj.project.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
