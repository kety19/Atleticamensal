package com.atletica.mensal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atletica.mensal.Entities.RankingEntity;

@Repository
public interface RankingRepository extends JpaRepository<RankingEntity, Integer>{
	
	Optional<RankingEntity> findById(Long atletica_id);
	
	 //List<RankingEntity> findByAtleticaId(Long atletica_id);
	 

}