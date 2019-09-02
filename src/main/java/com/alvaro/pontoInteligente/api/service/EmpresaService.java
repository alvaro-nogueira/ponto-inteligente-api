package com.alvaro.pontoInteligente.api.service;

import java.util.Optional;

import com.alvaro.pontoInteligente.api.entities.Empresa;

public interface EmpresaService {

	/*
	 * Retorna uma empresa dado um CNPJ
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);

	/*
	 * Cadastra uma empresa no bd
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);

}
