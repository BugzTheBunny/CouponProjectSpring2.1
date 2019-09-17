package com.slava.proj.project.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.slava.proj.project.repo.LogRepository;

public class MiniLogingUtil {

	@Autowired
	private LogRepository log;

}
