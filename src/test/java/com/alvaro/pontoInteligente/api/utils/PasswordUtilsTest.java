package com.alvaro.pontoInteligente.api.utils;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alvaro.pontoInteligente.api.util.PasswordUtils;

public class PasswordUtilsTest {

	private static final String SENHA = "123456";
	private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

	@Test
	public void testSenhaNula() {

		assertNull(PasswordUtils.gerarBCrypt(null));

	}

	@Test
	public void testGerarHashSenha() {

		String hash = PasswordUtils.gerarBCrypt(SENHA);

		assertTrue(bCryptEncoder.matches(SENHA, hash));

	}

}
