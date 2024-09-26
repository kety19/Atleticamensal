package com.atletica.mensal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Entities.PostagemEntity;

public interface AtleticaRepository extends JpaRepository<AtleticaEntity, Long> {
	
	 List<AtleticaEntity> findAllByOrderByPontuacaoTotalDesc();
}