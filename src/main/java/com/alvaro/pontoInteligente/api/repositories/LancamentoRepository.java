package com.alvaro.pontoInteligente.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.alvaro.pontoInteligente.api.entities.Lancamento;

@NamedQueries({
	@NamedQuery(name = "LancamentoRepository.findByFuncionarioId", 
			query = "SELECT lanc FROM Lancamento lanc WHERE lcan.funcionario.id = :funcionarioId")
})
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	List<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long fuincionarioId);

	Page<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long fuincionarioId, Pageable pageable);

}