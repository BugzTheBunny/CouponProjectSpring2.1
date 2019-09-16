package com.slava.proj.project.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
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
			@NotNull int amount, LocalDate start_date, LocalDate end_date, CType type, CStatus status) {
		this.id = id;
		this.title = title;
		this.message = message;
		this.price = price;
		this.amount = amount;
		this.start_date = start_date;
		this.end_date = end_date;
		this.type = type;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", message=" + message + ", price=" + price + ", amount="
				+ amount + ", start_date=" + start_date + ", end_date=" + end_date + ", type=" + type + ", status="
				+ status + "]";
	}

}
