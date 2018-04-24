package com.example.SanTest.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class AccountTest {

	@Autowired
	private JacksonTester<Account> jacksonTester;

	@Test
	public void testSerialise() throws IOException {
		Account account = new Account();
		account.setId(1l);
		account.setName("Name1");

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode node = objectMapper.createObjectNode();
		node.put("id", 1l);
		node.put("name", "Name1");
		String expectedAccountJson  = objectMapper.writeValueAsString(node);

		assertThat(jacksonTester.write(account)).isEqualToJson(expectedAccountJson.getBytes());

		assertThat(jacksonTester.write(account)).hasJsonPathNumberValue("@.id");
		assertThat(jacksonTester.write(account)).extractingJsonPathStringValue("@.name").isEqualTo("Name1");
	}

	@Test
	public void testDeserialise() throws IOException {
		Account account = new Account();
		account.setId(1l);
		account.setName("Name1");

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode node = objectMapper.createObjectNode();
		node.put("id", 1l);
		node.put("name", "Name1");
		String accountJson  = objectMapper.writeValueAsString(node);

//		assertThat(jacksonTester.parse(accountJson)).isEqualTo(account);

		assertThat(jacksonTester.parseObject(accountJson).getName()).isEqualTo("Name1");
	}
}