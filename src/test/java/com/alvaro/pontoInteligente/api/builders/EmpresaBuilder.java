package com.alvaro.pontoInteligente.api.builders;

import java.util.Date;
import java.util.List;

import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.entities.Funcionario;

public class EmpresaBuilder {

	private Long id;
	private String razaoSocial;
	private String cnpj;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private List<Funcionario> funcionarios;

	public EmpresaBuilder(Long id, String razaoSocial, String cnpj, Date dataCriacao, Date dataAtualizacao, List<Funcionario> funcionarios) {

		this.id = id;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.funcionarios = funcionarios;

	}

	public EmpresaBuilder() {}

	public EmpresaBuilder withId(Long id) {

		this.id = id;
		return this;

	}

	public EmpresaBuilder withRazaoSocial(String razaoSocial) {

		this.razaoSocial = razaoSocial;
		return this;

	}

	public EmpresaBuilder withCnpj(String cnpj) {

		this.cnpj = cnpj;
		return this;

	}

	public EmpresaBuilder withDataCriacao(Date dataCriacao) {

		this.dataCriacao = dataCriacao;
		return this;

	}

	public EmpresaBuilder withDataAtualizacao(Date dataAtualizacao) {

		this.dataAtualizacao = dataAtualizacao;
		return this;

	}

	public EmpresaBuilder withFuncionarios(List<Funcionario> funcionarios) {

		this.funcionarios = funcionarios;
		return this;

	}

	public Empresa build() {
		return new Empresa(id, razaoSocial, cnpj, dataCriacao, dataAtualizacao, funcionarios);
	}

}
