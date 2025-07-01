package com.project.quadroPresenca.quadroPresenca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.quadroPresenca.quadroPresenca.entities.Posto;


@Repository
public interface  PostoRepository extends JpaRepository<Posto,Integer> {

}
