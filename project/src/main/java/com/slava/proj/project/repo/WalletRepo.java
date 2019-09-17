package com.slava.proj.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slava.proj.project.models.CompanyWallet;

public interface WalletRepo extends JpaRepository<CompanyWallet, Long> {

	CompanyWallet findById(long i);

}