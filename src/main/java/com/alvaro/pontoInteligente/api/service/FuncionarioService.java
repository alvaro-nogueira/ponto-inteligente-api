package com.alvaro.pontoInteligente.api.service;

import java.util.Optional;

import com.alvaro.pontoInteligente.api.entities.Funcionario;

public interface FuncionarioService {

	/*
	 * Persiste um funcionario na base de dados
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persistir(Funcionario funcionario);
	
	/*
	 * Retorna um funcionario dado um CPF
	 * 
	 * @param cpf
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	/*
	 * Retorna um funcionario dado um Email
	 * 
	 * @param email
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/*
	 * Retorna um funcionario dado um Email
	 * 
	 * @param cpf
	 * @param email
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorCpfOrEmail(String cpf, String email);
	
	/*
	 * Retorna um funcionario dado um Email
	 * 
	 * @param id
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorId(Long id);
	
}
