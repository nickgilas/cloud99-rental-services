package com.cloud99.rental.controller;

import com.cloud99.rental.dataCreation.DataCreator;
import com.cloud99.rental.domain.account.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@Autowired
	private DataCreator dataCreator;

	@GetMapping("/loadTestData")
	@ResponseBody
	public Account test(@RequestParam String name) {
		Account acct = dataCreator.execute();
		return acct;
	}
}
