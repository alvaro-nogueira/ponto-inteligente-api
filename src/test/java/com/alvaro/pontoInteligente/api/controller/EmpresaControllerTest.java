package com.alvaro.pontoInteligente.api.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alvaro.pontoInteligente.api.builders.EmpresaBuilder;
import com.alvaro.pontoInteligente.api.entities.Empresa;
import com.alvaro.pontoInteligente.api.service.EmpresaService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmpresaControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmpresaService empresaService;

	private static final String BUSCAR_EMPRESA_CNPJ_URL = "/empresas/cnpj/";
	private static final Long ID = Long.valueOf(2);
	private static final String CNPJ = "10051369000187";
	private static final String RAZAO_SOCIAL = "Empresa XYZ";

	@Test
	@WithMockUser
	public void testBuscarEmpresaCnpjInvalido() throws Exception {
	
		BDDMockito.given(this.empresaService.buscarPorCnpj(Mockito.anyString())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_EMPRESA_CNPJ_URL + CNPJ).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Empresa não encontrada para o CNPJ " + CNPJ));
	}

	@Test
	@WithMockUser
	public void testBuscarEmpresaCnpjValido() throws Exception {

		BDDMockito.given(this.empresaService.buscarPorCnpj(Mockito.anyString()))
				.willReturn(Optional.of(this.obterDadosEmpresa()));

		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_EMPRESA_CNPJ_URL + CNPJ)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(ID))
				.andExpect(jsonPath("$.razaoSocial", equalTo(RAZAO_SOCIAL)))
				.andExpect(jsonPath("$.cnpj", equalTo(CNPJ)));

	}

	private Empresa obterDadosEmpresa() {
		
		return new EmpresaBuilder()
			.withId(ID)
			.withRazaoSocial(RAZAO_SOCIAL)
			.withCnpj(CNPJ)
			.build();

	}

}