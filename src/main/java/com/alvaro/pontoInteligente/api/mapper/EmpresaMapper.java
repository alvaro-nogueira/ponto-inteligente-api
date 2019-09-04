package com.alvaro.pontoInteligente.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.alvaro.pontoInteligente.api.dto.EmpresaDto;
import com.alvaro.pontoInteligente.api.entities.Empresa;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmpresaMapper {

	EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

	
	EmpresaDto empresaParaEmpresaDto(Empresa empresa);

	Empresa cadastroDtoParaFuncionario(EmpresaDto empresaDto);


}
