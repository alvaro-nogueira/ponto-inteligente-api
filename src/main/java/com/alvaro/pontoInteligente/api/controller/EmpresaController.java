package com.alvaro.pontoInteligente.api.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.pontoInteligente.api.dto.EmpresaDto;
import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.exception.ResourceNotFoundException;
import com.alvaro.pontoInteligente.api.mapper.EmpresaMapper;
import com.alvaro.pontoInteligente.api.service.EmpresaService;


@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

	@Autowired
	private EmpresaService empresaService;

	public EmpresaController() {
	}

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<EmpresaDto> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {

		log.info("Buscando empresa por CNPJ: {}", cnpj);

		Empresa empresa = empresaService.buscarPorCnpj(cnpj)
				.orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada para o CNPJ " + cnpj));

		return ResponseEntity.ok(EmpresaMapper.INSTANCE.empresaParaEmpresaDto(empresa));

	}

}