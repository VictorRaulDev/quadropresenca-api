package com.project.quadroPresenca.quadroPresenca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.quadroPresenca.quadroPresenca.dtos.PostoRequestDto;
import com.project.quadroPresenca.quadroPresenca.dtos.PostoResponseDto;
import com.project.quadroPresenca.quadroPresenca.services.PostoService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/posto")
public class PostoController {

	//injecao de dependencia
	@Autowired PostoService postoservice;
	
	
	@Operation(summary = "Serviço para criação de postos.")
	@PostMapping("criar-postos")
	public ResponseEntity<PostoResponseDto> criar(@RequestBody PostoRequestDto request) {
		var response = postoservice.criarPosto(request);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Serviço para listar de postos.")
	@GetMapping("listar-postos")
	public ResponseEntity<List<PostoResponseDto>> listarPostos() {

		try {
			var response = postoservice.listarPostos();
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build(); // Retorna erro 500 em caso de exceção
		}
	}
	
}
