package com.slava.proj.project.models;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slava.proj.project.enums.CStatus;
import com.slava.proj.project.enums.CType;

@Entity
public class Coupon {

	@Id
	@Column
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String title;
	@NotNull
	private String message;
	@NotNull
	private double price;
	@NotNull
	private int amount;
	@JsonIgnore
	@Column(name = "start_date")
	private Date startdate = Date.valueOf(LocalDate.now());
	@Column(name = "end_Date")
	private Date enddate;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(32) default 'OTHER'")
	private CType type = CType.FOOD;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(32) default 'ONSALE'")
	private CStatus status = CStatus.ONSALE;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStart_date(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public CType getType() {
		return type;
	}

	public void setType(CType type) {
		this.type = type;
	}

	public CStatus getStatus() {
		return status;
	}

	public void setStatus(CStatus status) {
		this.status = status;
	}

	public Coupon() {

	}

	public Coupon(@NotNull long id, @NotNull String title, @NotNull String message, @NotNull double price,
			@NotNull int amount, Date startdate, Date enddate, CType type, CStatus status) {
		this.id = id;
		this.title = title;
		this.message = message;
		this.price = price;
		this.amount = amount;
		this.startdate = startdate;
		this.enddate = enddate;
		this.type = type;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", message=" + message + ", price=" + price + ", amount="
				+ amount + ", start_date=" + startdate + ", end_date=" + enddate + ", type=" + type + ", status="
				+ status + "]";
	}

}
