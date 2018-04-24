package com.example.SanTest.service;

import com.example.SanTest.dto.Account;
import com.example.SanTest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account createAccount(Account account){
		return accountRepository.save(account);
	}

	public Account getAccount(long id){
		return accountRepository.findOne(id);
	}

	public Account updateAccount(Account account){
		return accountRepository.save(account);
	}

	public List<Account> getAllAccounts(){
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		Pageable page = new PageRequest(0,2, sort);
		return accountRepository.findAll();
	}

	public void deleteAccount(long id) {
		accountRepository.delete(id);
	}
}
