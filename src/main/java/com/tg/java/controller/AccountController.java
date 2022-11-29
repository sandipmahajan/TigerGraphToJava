package com.tg.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tg.java.model.Account;
import com.tg.java.repository.AccountRepository;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountRepository repo;
	
	@GetMapping("/findbyname/{name}")
    public List<Account> findAccountByName(@PathVariable(value = "name") String name) {
		System.out.println("Controller Name : "+name);
		return repo.getAccountByName(name);
        
    }
	
	@GetMapping("/all")
    public List<Account> getAllAccount() {
		
		return repo.getAllAccount();
        
    }

}
