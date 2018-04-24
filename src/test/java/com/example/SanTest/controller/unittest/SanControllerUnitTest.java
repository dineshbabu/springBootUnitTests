package com.example.SanTest.controller.unittest;

import com.example.SanTest.controller.SanController;
import com.example.SanTest.dto.Account;
import com.example.SanTest.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SanControllerUnitTest {

	@Autowired
	private SanController sanController;

	@MockBean
	private AccountService accountServiceMock;

	@MockBean
	private Account accountMock;


	@MockBean
	private List<Account> accountListMock;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void canGetAccount(){
		given(accountServiceMock.getAccount(anyLong())).willReturn(accountMock);
		sanController.getAccount(1l);
		verify(accountServiceMock,times(1)).getAccount(1l);
	}

	@Test
	public void canGetAllAccounts(){
		given(accountServiceMock.getAllAccounts()).willReturn(accountListMock);
		sanController.listAllAccounts();
		verify(accountServiceMock,times(1)).getAllAccounts();
	}

	@Test
	public void canCreateAccount(){
		given(accountServiceMock.createAccount(accountMock)).willReturn(accountMock);
		sanController.createAccount(accountMock);
		verify(accountServiceMock,times(1)).createAccount(accountMock);
	}

	@Test
	public void canDeleteAccount(){
		sanController.deleteAccount(1l);
		verify(accountServiceMock,times(1)).deleteAccount(1l);
	}

	@Test
	public void canUpdateAccount(){
		given(accountServiceMock.updateAccount(accountMock)).willReturn(accountMock);
		sanController.updateAccount(accountMock);
		verify(accountServiceMock,times(1)).updateAccount(accountMock);
	}

	@Test
	public void mockMvcGetTest() throws Exception {
		Account account = new Account();
		account.setName("Name1");
		account.setId(3);

		ObjectMapper objectMapper = new ObjectMapper();

		mockMvc.perform(MockMvcRequestBuilders.get("/account/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(account)));
	}
}

