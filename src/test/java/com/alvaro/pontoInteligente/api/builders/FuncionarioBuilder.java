package com.alvaro.pontoInteligente.api.builders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.entities.Funcionario;
import com.alvaro.pontoInteligente.api.entities.Lancamento;
import com.alvaro.pontoInteligente.api.enums.PerfilEnum;

public class FuncionarioBuilder {

	private Long id;
	private String nome = "Fulando de Tal";
	private String email = "teste@mail.com";
	private String senha = "123213";
	private String cpf = "40344662004";
	private BigDecimal valorHora;
	private Float qtdHorasTrabalhoDia;
	private Float qtdHorasAlmoco;
	private PerfilEnum perfil = PerfilEnum.ROLE_USUARIO;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Empresa empresa;
	private List<Lancamento> lancamentos;

	public FuncionarioBuilder(Long id, String nome, String email, String senha, String cpf, BigDecimal valorHora,
			Float qtdHorasTrabalhoDia, Float qtdHorasAlmoco, PerfilEnum perfil, Date dataCriacao, Date dataAtualizacao,
			Empresa empresa, List<Lancamento> lancamentos) {

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.valorHora = valorHora;
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
		this.qtdHorasAlmoco = qtdHorasAlmoco;
		this.perfil = perfil;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.empresa = empresa;
		this.lancamentos = lancamentos;

	}

	public FuncionarioBuilder() {}

	public FuncionarioBuilder withId(Long id) {

		this.id = id;
		return this;

	}

	public FuncionarioBuilder withNome(String nome) {

		this.nome = nome;
		return this;

	}

	public FuncionarioBuilder withEmail(String email) {

		this.email = email;
		return this;

	}

	public FuncionarioBuilder withDataCriacao(Date dataCriacao) {

		this.dataCriacao = dataCriacao;
		return this;

	}

	public FuncionarioBuilder withDataAtualizacao(Date dataAtualizacao) {

		this.dataAtualizacao = dataAtualizacao;
		return this;

	}

	public FuncionarioBuilder withCpf(String cpf) {

		this.cpf = cpf;
		return this;

	}

	public FuncionarioBuilder withValorHora(BigDecimal valorHora) {

		this.valorHora = valorHora;
		return this;

	}

	public FuncionarioBuilder withSenha(String senha) {

		this.senha = senha;
		return this;

	}

	public FuncionarioBuilder withQtdHorasTrabalhoDia(Float qtdHorasTrabalhoDia) {

		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
		return this;

	}

	public FuncionarioBuilder withQtdHorasAlmoco(Float qtdHorasAlmoco) {

		this.qtdHorasAlmoco = qtdHorasAlmoco;
		return this;

	}
	
	public FuncionarioBuilder withPerfil(PerfilEnum perfil) {

		this.perfil = perfil;
		return this;

	}
	
	public FuncionarioBuilder withEmpresa(Empresa empresa) {

		this.empresa = empresa;
		return this;

	}
	
	public FuncionarioBuilder withLancamentos(List<Lancamento> lancamentos) {

		this.lancamentos = lancamentos;
		return this;

	}

	public Funcionario build() {
		return new Funcionario(id, nome, email, senha, cpf, valorHora, qtdHorasTrabalhoDia,
				qtdHorasAlmoco, perfil, dataCriacao, dataAtualizacao, empresa, lancamentos);
	}

}
