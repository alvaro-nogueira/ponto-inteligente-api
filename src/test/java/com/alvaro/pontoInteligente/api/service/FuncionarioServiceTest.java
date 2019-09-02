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

import com.alvaro.pontoInteligente.api.entities.Funcionario;
import com.alvaro.pontoInteligente.api.repositories.FuncionarioRepository;

@SpringBootTest
@ActiveProfiles("test")
//@TestInstance(Lifecycle.PER_CLASS)
public class FuncionarioServiceTest {
	
	@MockBean
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private FuncionarioService funcionarioService;
	

	@BeforeEach
	public void setup() {

		BDDMockito.given(this.funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findByCpfOrEmail(Mockito.anyString(), Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Funcionario()));
		BDDMockito.given(this.funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());

	}


	@Test
	public void testPersistirFuncionario() {

		Funcionario funcionario = this.funcionarioService.persistir(new Funcionario());

		assertNotNull(funcionario);

	}

	@Test
	public void testBuscarPorCpf() {

		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorCpf("00000000000");

		assertTrue(funcionario.isPresent());

	}
	
	@Test
	public void testBuscarPorId() {

		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(1L);

		assertTrue(funcionario.isPresent());

	}
	
	@Test
	public void testBuscarPorEmail() {

		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorEmail("teste@mail.com");

		assertTrue(funcionario.isPresent());

	}
	
	@Test
	public void testBuscarPorCpfOrEmail() {

		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorCpfOrEmail("00000000000", "teste@mail.com");

		assertTrue(funcionario.isPresent());

	}



}
