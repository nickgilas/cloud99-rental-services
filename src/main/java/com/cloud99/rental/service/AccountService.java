package com.cloud99.rental.service;

import com.cloud99.rental.domain.account.Account;
import com.cloud99.rental.repo.AccountRepo;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class AccountService implements RentalService, CrudService<Account, AccountRepo> {

	@Autowired
	private AccountRepo acctRepo;

	@Override
	@Transactional
	public Account create(Account account) {

		LocalDateTime now = LocalDateTime.now();
		account.setCreateDate(now);
		account.setStatus(Account.Status.ACTIVE);

		return acctRepo.save(account);

	}

	@Override
	public Optional<Account> read(String id) {
		return acctRepo.findById(id);
	}

	@Override
	public Account update(Account account) {
		account.setUpdateDate(LocalDateTime.now());
		return acctRepo.save(account);
	}

	@Override
	public void delete(Account account) {
		acctRepo.delete(account);

	}

}
