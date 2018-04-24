package com.example.SanTest.controller;

import com.example.SanTest.dto.Account;
import com.example.SanTest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class SanController {

	@Value("${test.property}")
	private String testProperty;

	@Autowired
	private AccountService accountService;

//	@RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public String helloWorld(){
//		return "{message: Hello World}";
//	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account getAccount(@PathVariable("id") long id){
		return accountService.getAccount(id);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account updateAccount(@RequestBody Account account){
		return accountService.updateAccount(account);
	}

//	@RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account createAccount(@RequestBody Account account){
		return accountService.createAccount(account);
	}

//	@RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> listAllAccounts(){
		return accountService.getAllAccounts();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAccount(@PathVariable("id") long id){
		accountService.deleteAccount(id);
	}
}
