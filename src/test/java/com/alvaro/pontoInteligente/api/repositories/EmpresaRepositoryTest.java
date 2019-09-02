package com.alvaro.pontoInteligente.api.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.alvaro.pontoInteligente.api.builders.EmpresaBuilder;
import com.alvaro.pontoInteligente.api.entities.Empresa;

//@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class EmpresaRepositoryTest {

	private static final String CNPJ = "51463645000100";

	@Autowired
	private EmpresaRepository empresaRepository;

	@BeforeAll
	public void setup() {

		Empresa empresa = new EmpresaBuilder()
				.withCnpj(CNPJ)
				.withRazaoSocial("Empresa Exemplo")
				.build();

		empresaRepository.save(empresa);

	}

	@AfterAll
	public void tearDown() {

		empresaRepository.deleteAll();

	}

	@Test
	public void testBuscarPorCnpj() {

		Empresa empresa = this.empresaRepository.findByCnpj(CNPJ);
	
		assertEquals(CNPJ, empresa.getCnpj());

	}

}
