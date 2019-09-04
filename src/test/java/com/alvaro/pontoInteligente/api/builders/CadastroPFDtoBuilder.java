package com.alvaro.pontoInteligente.api.builders;

import java.util.Optional;

import com.alvaro.pontoInteligente.api.dto.CadastroPFDto;

public class CadastroPFDtoBuilder {

	private Long id;
	private String nome = "Fulano";
	private String email = "fulano@mail.com";
	private String senha = "123456";
	private String cpf = "08107353005";
	private Optional<String> valorHora = Optional.empty();
	private Optional<String> qtdHorasTrabalhoDia = Optional.empty();
	private Optional<String> qtdHorasAlmoco = Optional.empty();
	private String cnpj = "47741024000165";

	public CadastroPFDtoBuilder() {}


	public CadastroPFDtoBuilder withNome(String nome) {

		this.nome = nome;
		return this;

	}

	public CadastroPFDtoBuilder withEmail(String email) {

		this.email = email;
		return this;

	}

	public CadastroPFDtoBuilder withSenha(String senha) {

		this.senha = senha;
		return this;

	}

	public CadastroPFDtoBuilder withCpf(String cpf) {

		this.cpf = cpf;
		return this;

	}

	public CadastroPFDtoBuilder withValorHora(String valorHora) {

		this.valorHora = Optional.of(valorHora);
		return this;

	}

	public CadastroPFDtoBuilder withQtdHorasTrabalhoDia(String qtdHorasTrabalhoDia) {

		this.qtdHorasTrabalhoDia = Optional.of(qtdHorasTrabalhoDia);
		return this;

	}

	public CadastroPFDtoBuilder withQtdHorasAlmoco(String qtdHorasAlmoco) {

		this.qtdHorasAlmoco = Optional.of(qtdHorasAlmoco);
		return this;

	}

	public CadastroPFDtoBuilder withCnpj(String cnpj) {

		this.cnpj = cnpj;
		return this;

	}


	public CadastroPFDto build () {

		return new CadastroPFDto(id, nome, email, senha, cpf, valorHora, 
				qtdHorasTrabalhoDia, qtdHorasAlmoco, cnpj);

	}


}
