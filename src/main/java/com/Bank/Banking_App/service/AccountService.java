package com.Bank.Banking_App.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.Bank.Banking_App.Dto.AccountDto;
import com.Bank.Banking_App.Mapper.AccountMapper;
import com.Bank.Banking_App.entity.Account;
import com.Bank.Banking_App.repository.AccountRepo;

public class AccountService {
	@Autowired
	private AccountRepo accountRepo;
	
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedacc=accountRepo.save(account);
		AccountDto acc=AccountMapper.mapToAccountDto(account);
		return acc;
	}
	
	public AccountDto getAccountById(Long id) {
		Account account=accountRepo.findById(id).orElseThrow(()->new RuntimeException("Account Not Found"));
		AccountDto acc=AccountMapper.mapToAccountDto(account);
		return acc;
	}
	
	public AccountDto Deposit(Long id,double amount) {
		Account account=accountRepo.findById(id).orElseThrow(()->new RuntimeException("Account Not Found"));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account saved=accountRepo.save(account);
		return AccountMapper.mapToAccountDto(saved);
	}
	
	public AccountDto withdraw(Long id,double amount) {
		Account account=accountRepo.findById(id).orElseThrow(()->new RuntimeException("Account Not Found"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient amount");
		}
		
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account savedAcc=accountRepo.save(account);
		
		return AccountMapper.mapToAccountDto(savedAcc);
		
	}
	
	public List<AccountDto> getAllAccounts(){
		List<Account> accounts=accountRepo.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
		.collect(Collectors.toList());
	}
	
	public void deleteAccount(Long id) {
		Account account=accountRepo.findById(id).orElseThrow(()->new RuntimeException("Account Not Found"));
		accountRepo.deleteById(id);
	}
}
