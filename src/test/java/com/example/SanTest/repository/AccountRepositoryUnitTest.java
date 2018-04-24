package com.example.SanTest.repository;

import com.example.SanTest.dto.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryUnitTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void canCreateAccount(){
		Account expectedAccount = new Account();
		expectedAccount.setName("RepoTest");

		testEntityManager.persist(expectedAccount);

		Account actualAccount = accountRepository.findByName("RepoTest");

		assertTrue(expectedAccount.getName().equals(actualAccount.getName()));

	}
}