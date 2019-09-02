package com.alvaro.pontoInteligente.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvaro.pontoInteligente.api.entities.Funcionario;
import com.alvaro.pontoInteligente.api.repositories.FuncionarioRepository;
import com.alvaro.pontoInteligente.api.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger logger = LoggerFactory.getLogger(FuncionarioService.class);

	FuncionarioRepository funcionarioRepository;

	@Autowired
	public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	@Override
	public Funcionario persistir(Funcionario funcionario) {

		logger.info("Persistindo funcionário: {}", funcionario);

		return this.funcionarioRepository.save(funcionario);

	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {

		logger.info("Buscando funcionário pelo CPF {}", cpf);

		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));

	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {

		logger.info("Buscando funcionário pelo email {}", email);

		return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));

	}

	@Override
	public Optional<Funcionario> buscarPorCpfOrEmail(String cpf, String email) {

		logger.info("Buscando funcionário pelo email {}", email);

		return Optional.ofNullable(this.funcionarioRepository.findByCpfOrEmail(cpf, email));

	}

	@Override
	public Optional<Funcionario> buscarPorId(Long id) {

		logger.info("Buscando funcionário pelo email {}", id);

		return this.funcionarioRepository.findById(id);

	}

}
