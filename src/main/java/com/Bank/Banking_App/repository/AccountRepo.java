package com.Bank.Banking_App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bank.Banking_App.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long>{
	
}
