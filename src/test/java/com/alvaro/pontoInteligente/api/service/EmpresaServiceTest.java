package com.alvaro.pontoInteligente.api.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.repositories.EmpresaRepository;

@SpringBootTest
@ActiveProfiles("test")
//@TestInstance(Lifecycle.PER_CLASS)
public class EmpresaServiceTest {

	@MockBean
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaService empresaService;

	private static final String CNPJ = "51463645000100";

	@BeforeEach
	public void setup() {

		BDDMockito.given(this.empresaRepository.findByCnpj(Mockito.anyString())).willReturn(new Empresa());
		BDDMockito.given(this.empresaRepository.save(Mockito.any(Empresa.class))).willReturn(new Empresa());

	}


	@Test
	public void testPersistirEmpresa() {

		Empresa empresa = this.empresaService.persistir(new Empresa());

		assertNotNull(empresa);

	}

	@Test
	public void testBuscarPorCnpj() {

		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(CNPJ);

		assertTrue(empresa.isPresent());

	}



}
