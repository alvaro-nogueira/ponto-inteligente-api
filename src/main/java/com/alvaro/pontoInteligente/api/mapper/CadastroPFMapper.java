package com.alvaro.pontoInteligente.api.mapper;

import java.math.BigDecimal;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.alvaro.pontoInteligente.api.dto.CadastroPFDto;
import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.entities.Funcionario;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CadastroPFMapper {

	CadastroPFMapper INSTANCE = Mappers.getMapper(CadastroPFMapper.class);

	@Mappings({
		@Mapping(source = "funcionario", target = "valorHora", qualifiedByName = "mapValorHoraOpt"),
		@Mapping(source = "funcionario", target = "qtdHorasTrabalhoDia", qualifiedByName = "mapQtdHorasTrabalhoDiaOpt"),
		@Mapping(source = "funcionario", target = "qtdHorasAlmoco", qualifiedByName = "mapQtdHorasAlmocoOpt"),
		@Mapping(source = "funcionario.id", target = "id")
	})
	CadastroPFDto empresaEFuncionarioParaCadastroPFDto(Empresa empresa, Funcionario funcionario);

	@Mappings({
		@Mapping(source = "cadastroPFDto", target = "valorHora", qualifiedByName = "mapValorHora"),
		@Mapping(source = "cadastroPFDto", target = "qtdHorasTrabalhoDia", qualifiedByName = "mapQtdHorasTrabalhoDia"),
		@Mapping(source = "cadastroPFDto", target = "qtdHorasAlmoco", qualifiedByName = "mapQtdHorasAlmoco")
	})
	Funcionario cadastroPFDtoParaFuncionario(CadastroPFDto cadastroPFDto);

	@Mapping(ignore = true, target = "id")
	Empresa cadastroPFDtoParaEmpresa(CadastroPFDto cadastroPFDto);

	@Named("mapValorHoraOpt")
	default Optional<String> mapValorHoraOpt(Funcionario funcionario) {

		return funcionario.getValorHoraOpt()
				.map(String::valueOf)
				.map(Optional::of)
				.orElse(Optional.empty());

		// return funcionario.getValorHoraOpt().isPresent() ? Optional.of(funcionario.getValorHoraOpt().get().toString()) : Optional.empty();

	}
	
	@Named("mapQtdHorasTrabalhoDiaOpt")
	default Optional<String> mapQtdHorasTrabalhoDiaOpt(Funcionario funcionario) {

		return funcionario.getQtdHorasTrabalhoDiaOpt().isPresent() ? Optional.of(funcionario.getQtdHorasTrabalhoDiaOpt().get().toString()) : Optional.empty();

	}
	
	@Named("mapQtdHorasAlmocoOpt")
	default Optional<String> mapQtdHorasAlmocoOpt(Funcionario funcionario) {

		return funcionario.getQtdHorasAlmocoOpt().isPresent() ? Optional.of(funcionario.getQtdHorasAlmocoOpt().get().toString()) : Optional.empty();

	}
	
	@Named("mapValorHora")
	default BigDecimal mapValorHora(CadastroPFDto cadastroPFDto) {

		return cadastroPFDto.getValorHora().map(BigDecimal::new).orElse(null);
		// return cadastroPFDto.getValorHora().isPresent() ? new BigDecimal(cadastroPFDto.getValorHora().get()) : null;

	}
	
	@Named("mapQtdHorasTrabalhoDia")
	default Float mapQtdHorasTrabalhoDia(CadastroPFDto cadastroPFDto) {

		return cadastroPFDto.getQtdHorasTrabalhoDia().map(Float::valueOf).orElse(null);
		//return cadastroPFDto.getValorHora().isPresent() ? Float.valueOf(cadastroPFDto.getValorHora().get()) : null;

	}
	
	@Named("mapQtdHorasAlmoco")
	default Float mapQtdHorasAlmoco(CadastroPFDto cadastroPFDto) {
		
		return cadastroPFDto.getQtdHorasAlmoco().map(Float::valueOf).orElse(null);

		// return cadastroPFDto.getQtdHorasAlmoco().isPresent() ? Float.valueOf(cadastroPFDto.getValorHora().get()) : null;

	}

}
