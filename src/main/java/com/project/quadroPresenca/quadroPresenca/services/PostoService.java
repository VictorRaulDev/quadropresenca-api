package com.project.quadroPresenca.quadroPresenca.services;

import com.project.quadroPresenca.quadroPresenca.dtos.OficialCivilResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.quadroPresenca.quadroPresenca.dtos.PostoRequestDto;
import com.project.quadroPresenca.quadroPresenca.dtos.PostoResponseDto;
import com.project.quadroPresenca.quadroPresenca.entities.Posto;
import com.project.quadroPresenca.quadroPresenca.repositories.PostoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostoService {

	//injecao de dependencia
	@Autowired PostoRepository postorepository;


	//metodo para criar posto
	public PostoResponseDto criarPosto(PostoRequestDto request) {

		var posto = new Posto();
		posto.setPosto(request.getPosto());
		//salvar no banco
		postorepository.save(posto);
		posto.setId(posto.getId());
		posto.setPosto(posto.getPosto());
		
		//Retorna o posto criado
		var response = new PostoResponseDto();
		response.setId(posto.getId());
		response.setPosto(posto.getPosto());
		return response;
		
	}

	public List<PostoResponseDto> listarPostos() {

		var mapper = new ModelMapper();

		try {

			var postos = postorepository.findAll();

			var response = new ArrayList<PostoResponseDto>();

			for (var posto : postos) {
				response.add(mapper.map(posto, PostoResponseDto.class));
			}

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
