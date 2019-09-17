package com.slava.proj.project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class CompanyWallet {

	@Id
	long id = 1;
	@NotNull
	double income;
	@NotNull
	long transactions;

	public long getTransactions() {
		return transactions;
	}

	public void setTransactions(long transactions) {
		this.transactions = transactions;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public CompanyWallet(double income) {
		this.income = income;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = 1;
	}

	public CompanyWallet() {
	}

	public CompanyWallet(long id, @NotNull double income, @NotNull long transactions) {
		this.id = id;
		this.income = income;
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "CompanyWallet [id=" + id + ", income=" + income + "]";
	}

}
