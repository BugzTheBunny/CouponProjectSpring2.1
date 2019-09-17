package com.slava.proj.project.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Log {

	@Id
	@Column(name = "log_id")
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String message;
	Date date;

	public Log() {
	}

	public Log(@NotNull long id, String message, Date date) {
		this.id = id;
		this.message = message;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
