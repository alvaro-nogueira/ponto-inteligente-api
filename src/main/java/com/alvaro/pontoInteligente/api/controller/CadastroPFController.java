package com.alvaro.pontoInteligente.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.pontoInteligente.api.dto.CadastroPFDto;
import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.entities.Funcionario;
import com.alvaro.pontoInteligente.api.enums.PerfilEnum;
import com.alvaro.pontoInteligente.api.exception.ApiValidationException;
import com.alvaro.pontoInteligente.api.mapper.CadastroPFMapper;
import com.alvaro.pontoInteligente.api.response.Response;
import com.alvaro.pontoInteligente.api.service.EmpresaService;
import com.alvaro.pontoInteligente.api.service.FuncionarioService;

@RestController
@RequestMapping("/cadastrar-pf")
@CrossOrigin(origins = "*")
public class CadastroPFController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPFController.class);

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private FuncionarioService funcionarioService;

	public CadastroPFController() {
	}

	/**
	 * Cadastra um funcionário pessoa física no sistema.
	 * 
	 * @param cadastroPFDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPFDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroPFDto>> cadastrar(@Valid @RequestBody CadastroPFDto cadastroPFDto,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando PF: {}", cadastroPFDto.toString());
		Response<CadastroPFDto> response = new Response<CadastroPFDto>();

		validarDadosExistentes(cadastroPFDto, result);

		if (result.hasErrors()) {

			throw new ApiValidationException("Erros encontrados ao validar o objeto: " + result.getObjectName(), result.getAllErrors());

		}

		Empresa empresa = CadastroPFMapper.INSTANCE.cadastroPFDtoParaEmpresa(cadastroPFDto);
		Funcionario funcionario = CadastroPFMapper.INSTANCE.cadastroPFDtoParaFuncionario(cadastroPFDto);
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);

		Optional<Empresa> empresaOpt = this.empresaService.buscarPorCnpj(cadastroPFDto.getCnpj());
		empresaOpt.ifPresent(emp -> funcionario.setEmpresa(emp));
		this.funcionarioService.persistir(funcionario);

		response.setData(CadastroPFMapper.INSTANCE.empresaEFuncionarioParaCadastroPFDto(empresa, funcionario));

		return ResponseEntity.ok(response);

	}

	/**
	 * Verifica se a empresa está cadastrada e se o funcionário não existe na base de dados.
	 * 
	 * @param cadastroPFDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroPFDto cadastroPFDto, BindingResult result) {

		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(cadastroPFDto.getCnpj());

		if (!empresa.isPresent()) {
			result.addError(new ObjectError("empresa", "Empresa não cadastrada."));
		}

		this.funcionarioService.buscarPorCpf(cadastroPFDto.getCpf())
			.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcionarioService.buscarPorEmail(cadastroPFDto.getEmail())
			.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));

	}


}