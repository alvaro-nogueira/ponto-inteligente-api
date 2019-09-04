package com.alvaro.pontoInteligente.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	
	@GetMapping(value = "/test")
	public ResponseEntity<String> buscarPorCnpj() {
		
		
		return ResponseEntity.ok("teste");
	}
	

}
