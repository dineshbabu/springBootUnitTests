package com.example.SanTest.repository;

import com.example.SanTest.dto.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void testCreateAccount(){
		Account expectedAccount = new Account();
		expectedAccount.setName("Name1");

		testEntityManager.persist(expectedAccount);
		Account actualAccount = accountRepository.findByName("Name1");
		assertThat(actualAccount.getName().equals(expectedAccount.getName()));
	}
}