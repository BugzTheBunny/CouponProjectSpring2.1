package com.slava.proj.project.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.slava.proj.project.enums.CStatus;
import com.slava.proj.project.enums.CType;

@Entity
public class Coupon {

	@Id
	@Column
	@NotNull
	private long id;
	@NotNull
	private String title;
	@NotNull
	private String message;
	@NotNull
	private double price;
	@NotNull
	private int amount;
	@Column(name = "start_date")
	private LocalDate start_date = LocalDate.now();
	@Column(name = "end_Date")
	private LocalDate end_date;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(32) default 'OTHER'")
	private CType type = CType.FOOD;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(32) default 'ONSALE'")
	private CStatus status = CStatus.ONSALE;
}
