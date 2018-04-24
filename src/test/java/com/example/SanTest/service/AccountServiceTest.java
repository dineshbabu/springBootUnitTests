package com.example.SanTest.service;

import com.example.SanTest.dto.Account;
import com.example.SanTest.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService = new AccountService();

	@Mock
	private AccountRepository accountRepositoryMock;

	@Mock
	private Account accountMock;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void createAccount() throws Exception {
//		given(accountRepositoryMock.save(accountMock)).willReturn(accountMock);
		when(accountRepositoryMock.save(accountMock)).thenReturn(accountMock);
		accountService.createAccount(accountMock);
		verify(accountRepositoryMock,times(1)).save(accountMock);
	}

	@Test
	public void getAccount() throws Exception {

	}

	@Test
	public void updateAccount() throws Exception {
	}

	@Test
	public void getAllAccounts() throws Exception {
	}

	@Test
	public void deleteAccount() throws Exception {
	}

}