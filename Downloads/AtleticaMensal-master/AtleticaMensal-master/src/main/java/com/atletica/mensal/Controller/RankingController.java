package com.atletica.mensal.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atletica.mensal.Entities.RankingEntity;
import com.atletica.mensal.Repository.RankingRepository;
import com.atletica.mensal.Service.RankingService;

@RestController
@RequestMapping("/ranking")
public class RankingController {

	@Autowired
	private RankingService rankingService;
	
	@Autowired
	private RankingRepository rankingRepository;

	@PutMapping(value = "/atualizarPontuacao/{atletica_id}/{pontos}")
	public ResponseEntity<Integer> atualizarPontuacao (@PathVariable Integer atletica_id,@PathVariable int pontos) {
	    System.out.println("Recebido atletica_id: " + atletica_id);  // Adicionando log para verificar o ID recebido

		Optional<RankingEntity> rankingOpt = rankingRepository.findById(atletica_id);
				
		if(rankingOpt.isPresent()) {
			RankingEntity ranking = rankingOpt.get();
			
			ranking.setPontuacaoTotal(ranking.getPontuacaoTotal() + pontos);
			var resultado = rankingRepository.save(ranking);
			return ResponseEntity.ok(resultado.getPontuacao_total());
			//return ResponseEntity.ok("Pontuação atualizada com sucesso para a atlética ID: " + atletica_id);
			   
		} else {
			System.out.println("atletica not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
					
					
				
		
	}
	
	

}
