package com.alvaro.pontoInteligente.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.alvaro.pontoInteligente.api.dto.CadastroPJDto;
import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.entities.Funcionario;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CadastroPJMapper {

	CadastroPJMapper INSTANCE = Mappers.getMapper(CadastroPJMapper.class);

	@Mapping(source = "funcionario.id", target = "id")
	CadastroPJDto empresaEFuncionarioParaCadastroPJDto(Empresa empresa, Funcionario funcionario);

	Funcionario cadastroDtoParaFuncionario(CadastroPJDto cadastroPJDto);

	@Mapping(ignore = true, target = "id")
	Empresa cadastroDtoParaEmpresa(CadastroPJDto cadastroPJDto);

}
