package com.alvaro.pontoInteligente.api.service;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.alvaro.pontoInteligente.api.entities.Lancamento;
import com.alvaro.pontoInteligente.api.repositories.LancamentoRepository;

@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {

	@MockBean
	LancamentoRepository lancamentoRepository;

	@Autowired
	LancamentoService lancamentoService;

	@BeforeEach
	public void setup() {

		BDDMockito.given(this.lancamentoRepository.findByFuncionarioId(Mockito.anyLong())).willReturn(new ArrayList<Lancamento>());
		BDDMockito.given(this.lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
			.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		BDDMockito.given(this.lancamentoRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Lancamento()));
		BDDMockito.given(this.lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());

	}

	@Test
	public void testBuscarLancamentoPorFuncionarioId() {

		List<Lancamento> lancamentos = lancamentoService.buscarPorFuncionarioId(1L);

		assertNotNull(lancamentos);

	}
	
	@Test
	public void testBuscarLancamentoPorFuncionarioIdPaginado() {

		Page<Lancamento> lancamentos = lancamentoService.buscarPorFuncionarioId(1L, PageRequest.of(0, 2));

		assertNotNull(lancamentos);

	}
	
	@Test
	public void testBuscarLancamentoPorId() {

		Optional<Lancamento> lancamento = lancamentoService.buscarPorId(1L);

		assertTrue(lancamento.isPresent());

	}
	
	@Test
	public void testPersistirLancamento() {

		Lancamento lancamento = lancamentoService.persistir(new Lancamento());

		assertNotNull(lancamento);

	}

}
