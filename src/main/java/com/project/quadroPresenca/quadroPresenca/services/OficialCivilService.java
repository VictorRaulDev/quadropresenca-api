package com.project.quadroPresenca.quadroPresenca.services;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.project.quadroPresenca.quadroPresenca.dtos.OficialCivilRequestDto;
import com.project.quadroPresenca.quadroPresenca.dtos.OficialCivilResponseDto;

import com.project.quadroPresenca.quadroPresenca.entities.OficialCivil;
import com.project.quadroPresenca.quadroPresenca.entities.Posto;
import com.project.quadroPresenca.quadroPresenca.repositories.OficialCivilRepository;



@Service
public class OficialCivilService {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	OficialCivilRepository oficialCivilRepository;

	public String salvarImagem(MultipartFile imagem, String subpastaDestino) throws IOException {
		// Caminho base relativo ao diretório atual da aplicação
		String basePath = new File(".").getCanonicalPath(); // retorna o caminho da pasta raiz

		// Monta o caminho completo para salvar a imagem
		String caminhoPasta = basePath + File.separator + subpastaDestino;
		File pasta = new File(caminhoPasta);
		if (!pasta.exists()) {
			pasta.mkdirs(); // Cria a pasta se não existir
		}

		// Nome e caminho final da imagem
		String nomeImagem = imagem.getOriginalFilename();
		String caminhoImagem = caminhoPasta + File.separator + nomeImagem;

		// Salva a imagem
		File destino = new File(caminhoImagem);
		imagem.transferTo(destino);

		// Retorna o caminho relativo (ex: "assets/images/foto.jpg")
		return subpastaDestino + "/" + nomeImagem;
	}

	public OficialCivilResponseDto criarOficialCivil(OficialCivilRequestDto request, MultipartFile imagem)
			throws IOException {

		String caminhoImagem = salvarImagem(imagem, "assets/images");

		var oficialCivil = new OficialCivil();
		oficialCivil.setPosto(new Posto());
		oficialCivil.setNome(request.getNome());
		oficialCivil.getPosto().setId(request.getPosto());
		oficialCivil.setAntiguidade(request.getAntiguidade());
		oficialCivil.setUrlFoto(caminhoImagem);

		// salvar no banco
		oficialCivilRepository.save(oficialCivil);

		// Retorna o posto criado
		var response = new OficialCivilResponseDto();

		response.setNome(oficialCivil.getNome());
		response.setPosto(oficialCivil.getPosto().getPosto());
		response.setAntiguidade(oficialCivil.getAntiguidade());
		response.setUrlFoto(oficialCivil.getUrlFoto());

		return response;

	}

	public List<OficialCivilResponseDto> listarOficiaisCivis() {

		try {

			var oficiaisCivis = oficialCivilRepository.findAllSort();

			var response = new ArrayList<OficialCivilResponseDto>();

			for (var oficialCivil : oficiaisCivis) {
				response.add(mapper.map(oficialCivil, OficialCivilResponseDto.class));
			}

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<OficialCivilResponseDto> listarOficialCivil() {

		try {

			var oficiaisCivis2 = oficialCivilRepository.findByAntiguidade(1);
			var response = new ArrayList<OficialCivilResponseDto>();
			
			for (var oficialCivil : oficiaisCivis2) {
				response.add(mapper.map(oficialCivil, OficialCivilResponseDto.class));
			}

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public OficialCivilResponseDto  presenca( Integer id) {

		OficialCivil oficialCivil = oficialCivilRepository.findById(id).orElse(null);
		var presenca = oficialCivil.getPresenca();

		if (presenca == false) {
				oficialCivil.setPresenca(true);
				oficialCivilRepository.save(oficialCivil);
				return mapper.map(oficialCivil, OficialCivilResponseDto.class);
		} else if (presenca == true) {
				oficialCivil.setPresenca(false);
				oficialCivilRepository.save(oficialCivil);
				return mapper.map(oficialCivil, OficialCivilResponseDto.class);
			} else {
				return null;
			}
		}
}
