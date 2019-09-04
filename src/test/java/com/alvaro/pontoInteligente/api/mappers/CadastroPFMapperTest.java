package com.alvaro.pontoInteligente.api.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.alvaro.pontoInteligente.api.builders.CadastroPFDtoBuilder;
import com.alvaro.pontoInteligente.api.builders.EmpresaBuilder;
import com.alvaro.pontoInteligente.api.builders.FuncionarioBuilder;
import com.alvaro.pontoInteligente.api.dto.CadastroPFDto;
import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.entities.Funcionario;
import com.alvaro.pontoInteligente.api.mapper.CadastroPFMapper;

@TestInstance(Lifecycle.PER_CLASS)
public class CadastroPFMapperTest {

	private Funcionario funcionario;
	private Empresa empresa;
	private CadastroPFDto cadastroPFDto;

	@BeforeAll
	public void setup() {

		empresa = new EmpresaBuilder()
				.build();

		funcionario = new FuncionarioBuilder()
				.withValorHora(new BigDecimal("50"))
				.withQtdHorasAlmoco(Float.valueOf("1"))
				.withQtdHorasTrabalhoDia(Float.valueOf("8"))
				.build();

		cadastroPFDto = new CadastroPFDtoBuilder()
				.withQtdHorasAlmoco("1")
				.withQtdHorasTrabalhoDia("8")
				.build();

	}


	@Test
	public void testEmpresaEFuncionarioParaCadastroPFDto() {

		CadastroPFDto cadastroPfDto = CadastroPFMapper.INSTANCE.empresaEFuncionarioParaCadastroPFDto(empresa, funcionario);

		assertEquals(cadastroPfDto.getCnpj(), empresa.getCnpj());

		assertEquals(cadastroPfDto.getNome(), funcionario.getNome());
		assertEquals(cadastroPfDto.getEmail(), funcionario.getEmail());
		assertEquals(cadastroPfDto.getSenha(), funcionario.getSenha());
		assertEquals(cadastroPfDto.getCpf(), funcionario.getCpf());
		assertEquals(cadastroPfDto.getValorHora().get(), String.valueOf(funcionario.getValorHora()));
		assertEquals(cadastroPfDto.getQtdHorasAlmoco().get(), String.valueOf(funcionario.getQtdHorasAlmoco()));
		assertEquals(cadastroPfDto.getQtdHorasTrabalhoDia().get(), String.valueOf(funcionario.getQtdHorasTrabalhoDia()));

	}
	
	@Test
	public void testEmpresaEFuncionarioParaCadastroPFDtoSemValorHora() {

		Funcionario funcionario = new FuncionarioBuilder()
				.withQtdHorasAlmoco(Float.valueOf("1"))
				.withQtdHorasTrabalhoDia(Float.valueOf("8"))
				.build();
	
		CadastroPFDto cadastroPfDto = CadastroPFMapper.INSTANCE.empresaEFuncionarioParaCadastroPFDto(empresa, funcionario);

		assertEquals(cadastroPfDto.getCnpj(), empresa.getCnpj());

		assertEquals(cadastroPfDto.getNome(), funcionario.getNome());
		assertEquals(cadastroPfDto.getEmail(), funcionario.getEmail());
		assertEquals(cadastroPfDto.getSenha(), funcionario.getSenha());
		assertEquals(cadastroPfDto.getCpf(), funcionario.getCpf());
		assertEquals(cadastroPfDto.getQtdHorasAlmoco().get(), String.valueOf(funcionario.getQtdHorasAlmoco()));
		assertEquals(cadastroPfDto.getQtdHorasTrabalhoDia().get(), String.valueOf(funcionario.getQtdHorasTrabalhoDia()));
		
		assertFalse(cadastroPfDto.getValorHora().isPresent());

	}


	@Test
	public void testCadastroPFDtoParaFuncionario() {

		CadastroPFDto cadastroPfDto = new CadastroPFDtoBuilder()
				.withQtdHorasAlmoco("1")
				.withQtdHorasTrabalhoDia("8")
				.withValorHora("20")
				.build();
		
		Funcionario funcionario = CadastroPFMapper.INSTANCE.cadastroPFDtoParaFuncionario(cadastroPfDto);

		assertEquals(cadastroPfDto.getNome(), funcionario.getNome());
		assertEquals(cadastroPfDto.getEmail(), funcionario.getEmail());
		assertEquals(cadastroPfDto.getSenha(), funcionario.getSenha());
		assertEquals(cadastroPfDto.getCpf(), funcionario.getCpf());
		assertEquals(new BigDecimal(cadastroPfDto.getValorHora().get()), funcionario.getValorHora());
		assertEquals(Float.valueOf(cadastroPfDto.getQtdHorasAlmoco().get()), funcionario.getQtdHorasAlmoco());
		assertEquals(Float.valueOf(cadastroPfDto.getQtdHorasTrabalhoDia().get()), funcionario.getQtdHorasTrabalhoDia());

	}
	
	@Test
	public void testCadastroPFDtoParaFuncionarioSemValorHora() {

		Funcionario funcionario = CadastroPFMapper.INSTANCE.cadastroPFDtoParaFuncionario(cadastroPFDto);

		assertEquals(cadastroPFDto.getNome(), funcionario.getNome());
		assertEquals(cadastroPFDto.getEmail(), funcionario.getEmail());
		assertEquals(cadastroPFDto.getSenha(), funcionario.getSenha());
		assertEquals(cadastroPFDto.getCpf(), funcionario.getCpf());
		assertEquals(Float.valueOf(cadastroPFDto.getQtdHorasAlmoco().get()), funcionario.getQtdHorasAlmoco());
		assertEquals(Float.valueOf(cadastroPFDto.getQtdHorasTrabalhoDia().get()), funcionario.getQtdHorasTrabalhoDia());

		assertNull(funcionario.getValorHora());

	}

	@Test
	public void testCadastroPFDtoParaEmpresa() {

		Empresa empresa = CadastroPFMapper.INSTANCE.cadastroPFDtoParaEmpresa(cadastroPFDto);

		assertEquals(cadastroPFDto.getCnpj(), empresa.getCnpj());

	}

}

