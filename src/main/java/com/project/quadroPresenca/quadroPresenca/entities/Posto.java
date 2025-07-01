package com.project.quadroPresenca.quadroPresenca.entities;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "posto")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Posto {
		
		@GeneratedValue(strategy = GenerationType.IDENTITY) 
		@Id
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "posto", length = 25, nullable = false, unique = true)
		private String posto;
		
		@OneToMany(mappedBy = "posto")
		private List<OficialCivil> oficiaisCivis;
	}

