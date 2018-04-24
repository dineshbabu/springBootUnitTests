package com.example.SanTest.controller.unittest;

import com.example.SanTest.controller.SanController;
import com.example.SanTest.dto.Account;
import com.example.SanTest.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SanControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SanController sanController;

	@MockBean
	private AccountService accountServiceMock;

	@MockBean
	private List<Account> accountListMock;

	@MockBean
	private Account accountMock;

	@Test
	public void canGetAccount(){
		given(accountServiceMock.getAccount(anyLong())).willReturn(accountMock);
		sanController.getAccount(1);
		verify(accountServiceMock,times(1)).getAccount(1);
	}

	@Test
	public void canCreateAccount(){
		Account account = new Account();
		account.setId(1l);
		account.setName("Name1");

		given(accountServiceMock.createAccount(account)).willReturn(account);

		sanController.createAccount(account);
		verify(accountServiceMock,times(1)).createAccount(account);
	}

	@Test
	public void canDeleteAccount(){
		sanController.deleteAccount(1l);
		verify(accountServiceMock,times(1)).deleteAccount(1l);
	}

	@Test
	public void canUpdateAccount(){
		Account account = new Account();
		account.setId(1l);
		account.setName("Name1");
		given(accountServiceMock.updateAccount(account)).willReturn(account);
		sanController.updateAccount(account);
		verify(accountServiceMock,times(1)).updateAccount(account);
	}

	@Test
	public void canGgetAllAccounts(){
		given(accountServiceMock.getAllAccounts()).willReturn(accountListMock);
		sanController.listAllAccounts();
		verify(accountServiceMock, times(1)).getAllAccounts();
	}
}