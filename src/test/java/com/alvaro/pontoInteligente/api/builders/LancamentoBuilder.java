package com.alvaro.pontoInteligente.api.builders;

import java.util.Date;

import com.alvaro.pontoInteligente.api.entities.Funcionario;
import com.alvaro.pontoInteligente.api.entities.Lancamento;
import com.alvaro.pontoInteligente.api.enums.TipoEnum;

public class LancamentoBuilder {

	private Long id;
	private Date data;
	private String descricao;
	private String localizacao;
	private TipoEnum tipo;
	private Date dataCriacao;
	private Date dataAtualizacao;
	
	private Funcionario funcionario;
	
	public LancamentoBuilder() {}

	public LancamentoBuilder(Long id, Date data, String descricao, String localizacao, TipoEnum tipo, Date dataCriacao,
			Date dataAtualizacao, Funcionario funcionario) {

		this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.tipo = tipo;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.funcionario = funcionario;

	}


	public LancamentoBuilder withId(Long id) {

		this.id = id;
		return this;

	}

	public LancamentoBuilder withData(Date data) {

		this.data = data;
		return this;

	}

	public LancamentoBuilder withDescricao(String descricao) {

		this.descricao = descricao;
		return this;

	}

	public LancamentoBuilder withLocalizacao(String localizacao) {

		this.localizacao = localizacao;
		return this;

	}
	
	public LancamentoBuilder withDataCriacao(Date dataCriacao) {

		this.dataCriacao = dataCriacao;
		return this;

	}

	public LancamentoBuilder withDataAtualizacao(Date dataAtualizacao) {

		this.dataAtualizacao = dataAtualizacao;
		return this;

	}
	
	public LancamentoBuilder withTipo(TipoEnum tipo) {

		this.tipo = tipo;
		return this;

	}

	public LancamentoBuilder withFuncionario(Funcionario funcionario) {

		this.funcionario = funcionario;
		return this;

	}

	public Lancamento build() {
		return new Lancamento(id, data, descricao, localizacao, tipo, dataCriacao, dataAtualizacao, funcionario);
	}
}
