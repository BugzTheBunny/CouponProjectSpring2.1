package com.slava.proj.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slava.proj.project.models.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

}
