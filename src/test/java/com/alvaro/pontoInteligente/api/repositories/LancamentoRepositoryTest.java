package com.alvaro.pontoInteligente.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.alvaro.pontoInteligente.api.builders.EmpresaBuilder;
import com.alvaro.pontoInteligente.api.builders.FuncionarioBuilder;
import com.alvaro.pontoInteligente.api.builders.LancamentoBuilder;
import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.entities.Funcionario;
import com.alvaro.pontoInteligente.api.entities.Lancamento;
import com.alvaro.pontoInteligente.api.enums.TipoEnum;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class LancamentoRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	private Long funcionarioId;

	@BeforeAll
	public void setup() {

		Empresa empresa = new EmpresaBuilder()
				.build();

		empresaRepository.save(empresa);

		Funcionario funcionario = new FuncionarioBuilder()
				.withEmpresa(empresa)
				.build();

		funcionarioRepository.save(funcionario);
		funcionarioId = funcionario.getId();

		lancamentoRepository.save(gerarLancamento(funcionario));
		lancamentoRepository.save(gerarLancamento(funcionario));

	}

	@AfterAll
	public void tearDown() {
		empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarLancamentoPorFuncionarioId() {

		List<Lancamento> lancamentos = lancamentoRepository.findByFuncionarioId(funcionarioId);
		assertEquals(2, lancamentos.size());

	}
	
	@Test
	public void testBuscarLancamentoPorFuncionarioIdPaginado() {

		PageRequest page = PageRequest.of(0, 2);
		Page<Lancamento> lancamentos = lancamentoRepository.findByFuncionarioId(funcionarioId, page);
		
		assertEquals(2, lancamentos.getTotalElements());

	}

	private Lancamento gerarLancamento(Funcionario funcionario) {

		return new LancamentoBuilder()
				.withData(new Date())
				.withTipo(TipoEnum.INICIO_ALMOCO)
				.withFuncionario(funcionario)
				.build();

	}


}
