package com.alvaro.pontoInteligente.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.repositories.EmpresaRepository;
import com.alvaro.pontoInteligente.api.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private static final Logger logger = LoggerFactory.getLogger(EmpresaService.class);

	EmpresaRepository empresaRepository;

	@Autowired
	public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}

	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {

		logger.info("Buscando empresa por CNPJ {} " + cnpj);
		
		//throw new RuntimeException("21312312");

		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));

	}

	@Override
	public Empresa persistir(Empresa empresa) {

		logger.info("Persistindo empresa: {} " + empresa);
		
		//throw new RuntimeException("21312312");

		return this.empresaRepository.save(empresa);

	}

}
