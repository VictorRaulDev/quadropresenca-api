package com.project.quadroPresenca.quadroPresenca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.quadroPresenca.quadroPresenca.entities.OficialCivil;



@Repository
public interface OficialCivilRepository extends JpaRepository<OficialCivil,Integer>  {

	@Query("""
		    SELECT o FROM OficialCivil o WHERE o.antiguidade = :antiguidade
		""")
		List<OficialCivil> findByAntiguidade(@Param("antiguidade") Integer antiguidade);

	@Query("""
		    SELECT o FROM OficialCivil o WHERE o.antiguidade > 1 ORDER BY o.antiguidade ASC
		""")
	List<OficialCivil> findAllSort();

}