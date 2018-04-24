package com.example.SanTest.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class AccountUnitTest {

	@Autowired
	private JacksonTester<Account> jacksonTester;

	@Test
	public void canSerailise() throws IOException {
		Account account = new Account();
		account.setId(1l);
		account.setName("Name1");

		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJson = objectMapper.writeValueAsString(account);

		assertThat(jacksonTester.write(account)).isEqualToJson(expectedJson);
		assertThat(jacksonTester.write(account)).hasJsonPathNumberValue("@.id");
		assertThat(jacksonTester.write(account)).extractingJsonPathStringValue("@.name").isEqualTo("Name1");
	}

	@Test
	public void canDeserialise() throws IOException {

		Account expectedAccount = new Account();
		expectedAccount.setId(1l);
		expectedAccount.setName("Name1");

		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJson = objectMapper.writeValueAsString(expectedAccount);

		Account actualAccount = jacksonTester.parse(expectedJson).getObject();
		assertThat(actualAccount.getName()).isEqualTo(expectedAccount.getName());
		assertThat(actualAccount.getId()).isEqualTo(expectedAccount.getId());
	}
}