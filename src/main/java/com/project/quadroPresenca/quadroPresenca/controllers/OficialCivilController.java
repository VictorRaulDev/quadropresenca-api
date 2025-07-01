package com.project.quadroPresenca.quadroPresenca.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.project.quadroPresenca.quadroPresenca.dtos.OficialCivilRequestDto;
import com.project.quadroPresenca.quadroPresenca.dtos.OficialCivilResponseDto;
import com.project.quadroPresenca.quadroPresenca.services.OficialCivilService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;


@RestController
@RequestMapping("/api/oficial-civil")
public class OficialCivilController {

	@Autowired
	OficialCivilService oficialcivilservice;

	@PostMapping(value = "/salvar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE  )
	public ResponseEntity<OficialCivilResponseDto> criarOficialCivil(@RequestPart("request") OficialCivilRequestDto request,
																	@RequestPart("imagem") MultipartFile imagem) {
		try {

			var response = oficialcivilservice.criarOficialCivil(request, imagem);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<List<OficialCivilResponseDto>> listarOficiaisCivis() {
		try {
			var response = oficialcivilservice.listarOficiaisCivis();
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/listar1")
	public ResponseEntity<List<OficialCivilResponseDto>> listarOficialCivil() {
		try {
			var response = oficialcivilservice.listarOficialCivil();
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return null;
		}
	}
	

}
