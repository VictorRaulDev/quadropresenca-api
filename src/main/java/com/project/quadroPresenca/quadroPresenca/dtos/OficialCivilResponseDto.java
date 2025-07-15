package com.project.quadroPresenca.quadroPresenca.dtos;

import lombok.Data;

@Data

public class OficialCivilResponseDto {
	private Integer id;
	private String nome;
	private String posto;
	private Integer antiguidade;
	private String urlFoto;
	private Boolean presenca;

}
