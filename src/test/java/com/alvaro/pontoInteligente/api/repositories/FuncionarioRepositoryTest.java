package com.alvaro.pontoInteligente.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.alvaro.pontoInteligente.api.builders.EmpresaBuilder;
import com.alvaro.pontoInteligente.api.builders.FuncionarioBuilder;
import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.entities.Funcionario;
import com.alvaro.pontoInteligente.api.enums.PerfilEnum;
import com.alvaro.pontoInteligente.api.util.PasswordUtils;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class FuncionarioRepositoryTest {

	private static final String CPF = "40344662004";
	private static final String EMAIL = "teste@mail.com";

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@BeforeAll
	public void setup() {

		Empresa empresa = new EmpresaBuilder()
				.build();

		empresaRepository.save(empresa);

		Funcionario funcionario = new FuncionarioBuilder()
				.withEmail(EMAIL)
				.withCpf(CPF)
				.withNome("Fulano de Tal")
				.withPerfil(PerfilEnum.ROLE_USUARIO)
				.withSenha(PasswordUtils.gerarBCrypt("123456"))
				.withEmpresa(empresa)
				.build();

		funcionarioRepository.save(funcionario);

	}

	@AfterAll
	public void tearDown() {

		empresaRepository.deleteAll();

	}

	@Test
	public void testBuscarFuncionarioPorCpf() {

		Funcionario funcionario = funcionarioRepository.findByCpf(CPF);

		assertEquals(CPF, funcionario.getCpf());

	}

	@Test
	public void testBuscarPorEmail() {

		Funcionario funcionario = funcionarioRepository.findByCpf(CPF);

		assertEquals(EMAIL, funcionario.getEmail());

	}

	@Test
	public void testBuscarPorCpfOrEmail() {

		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);

		assertNotNull(funcionario);

	}

	@Test
	public void testBuscarPorCpfOrEmailParaEmailInvalido() {

		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail(CPF, "invalido@teste.com");

		//assertNotNull(funcionario);
		assertEquals(CPF, funcionario.getCpf());
		assertNotEquals("invalido@teste.com", funcionario.getEmail());

	}

	@Test
	public void testBuscarPorCpfOrEmailParaCpfInvalido() {

		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail("00000000000", EMAIL);

		//assertNotNull(funcionario);
		assertEquals(EMAIL, funcionario.getEmail());
		assertNotEquals("00000000000", funcionario.getCpf());

	}

}
