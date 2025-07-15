package com.project.quadroPresenca.quadroPresenca.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "oficial_civil")
@Setter
@Getter
@ToString
public class OficialCivil {

	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;


	@Column(name = "urlfoto", length = 100, nullable = false)
    private String urlFoto;
	
	@Column(name = "antiguidade", length = 10, nullable = false)
	private Integer antiguidade;

	@Column(name = "presenca", nullable = false)
	private Boolean presenca;
	
	@ManyToOne //muitos oficiais/civis para 1 posto
	@JoinColumn(name = "posto_id", nullable = false)
	private Posto posto;
}

