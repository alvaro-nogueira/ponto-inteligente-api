package com.alvaro.pontoInteligente.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alvaro.pontoInteligente.api.entities.Lancamento;
import com.alvaro.pontoInteligente.api.repositories.LancamentoRepository;
import com.alvaro.pontoInteligente.api.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final Logger logger = LoggerFactory.getLogger(LancamentoService.class);

	LancamentoRepository lancamentoRepository;

	@Autowired
	public LancamentoServiceImpl(LancamentoRepository lancamentoRepository) {
		this.lancamentoRepository = lancamentoRepository;
	}

	@Override
	public Optional<Lancamento> buscarPorId(Long id) {

		logger.info("Buscando lançamento por ID {}", id);

		return this.lancamentoRepository.findById(id);

	}
	
	@Override
	public List<Lancamento> buscarPorFuncionarioId(Long funcionarioId) {
		logger.info("Buscando lançamentos para o funcionário ID {}", funcionarioId);

		return this.lancamentoRepository.findByFuncionarioId(funcionarioId);
	}

	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {

		logger.info("Buscando lançamentos para o funcionário ID {}", funcionarioId);

		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);

	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {

		logger.info("Persistindo o lançamento: {}", lancamento);

		return this.lancamentoRepository.save(lancamento);

	}

	@Override
	public void remover(Long id) {

		logger.info("Removendo o lançamento ID {}", id);

		this.lancamentoRepository.deleteById(id);

	}

}
