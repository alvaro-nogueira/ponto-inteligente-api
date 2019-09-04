package com.alvaro.pontoInteligente.api.controller;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.pontoInteligente.api.dto.CadastroPJDto;
import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.entities.Funcionario;
import com.alvaro.pontoInteligente.api.enums.PerfilEnum;
import com.alvaro.pontoInteligente.api.mapper.CadastroPJMapper;
import com.alvaro.pontoInteligente.api.response.Response;
import com.alvaro.pontoInteligente.api.service.EmpresaService;
import com.alvaro.pontoInteligente.api.service.FuncionarioService;
import com.alvaro.pontoInteligente.api.util.PasswordUtils;


@RestController
@RequestMapping("/cadastrar-pj")
public class CadastroPJController {

	private static final Logger logger = LoggerFactory.getLogger(CadastroPJController.class);

	private FuncionarioService funcionarioService;

	private EmpresaService empresaService;

	@Autowired
	public CadastroPJController(EmpresaService empresaService, FuncionarioService funcionarioService) {

		this.empresaService = empresaService;
		this.funcionarioService = funcionarioService;

	}

	/**
	 * Cadastra uma pessoa jurídica no sistema.
	 * 
	 * @param cadastroPJDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPJDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Validated @RequestBody CadastroPJDto cadastroPJDto,
			BindingResult result) throws NoSuchAlgorithmException {

		logger.info("Cadastrando PJ: {}", cadastroPJDto.toString());
		Response<CadastroPJDto> response = new Response<CadastroPJDto>();

		validarDadosExistentes(cadastroPJDto, result);
		Empresa empresa = CadastroPJMapper.INSTANCE.cadastroDtoParaEmpresa(cadastroPJDto);
		Funcionario funcionario = CadastroPJMapper.INSTANCE.cadastroDtoParaFuncionario(cadastroPJDto);
		
		if (result.hasErrors()) {

			logger.error("Erro validando dados de cadastro PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
	
		}

		empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.gerarBCrypt(cadastroPJDto.getSenha()));
		
		funcionarioService.persistir(funcionario);

		response.setData(CadastroPJMapper.INSTANCE.empresaEFuncionarioParaCadastroPJDto(empresa, funcionario));

		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se a empresa ou funcionário já existem na base de dados.
	 * 
	 * @param cadastroPJDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroPJDto cadastroPJDto, BindingResult result) {
		this.empresaService.buscarPorCnpj(cadastroPJDto.getCnpj())
				.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

		this.funcionarioService.buscarPorCpf(cadastroPJDto.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcionarioService.buscarPorEmail(cadastroPJDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
	}


}
