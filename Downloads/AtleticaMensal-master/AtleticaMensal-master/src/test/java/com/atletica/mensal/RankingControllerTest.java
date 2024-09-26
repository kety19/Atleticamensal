package com.atletica.mensal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.atletica.mensal.Controller.RankingController;
import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Entities.RankingEntity;
import com.atletica.mensal.Repository.AtleticaRepository;
import com.atletica.mensal.Repository.RankingRepository;

@SpringBootTest
public class RankingControllerTest {
	@Autowired
	RankingController rankingController;

	@MockBean
	RankingRepository rankingRepository;

	@MockBean
	AtleticaRepository atleticaRepository;

	@BeforeEach
	void setup() {
		AtleticaEntity atletica = new AtleticaEntity();
		atletica.setId(1L);
		atletica.setNome("Teste");

		RankingEntity ranking = new RankingEntity();
		ranking.setPontuacaoTotal(25);
		ranking.setAtleticaEntity(atletica); // Associar a Atletica ao Ranking

		Mockito.when(atleticaRepository.findById(1L)).thenReturn(Optional.of(atletica));
		Mockito.when(rankingRepository.findById(1L)).thenReturn(Optional.of(ranking));
		Mockito.when(rankingRepository.save(Mockito.any(RankingEntity.class))).thenReturn(ranking);
	}

	/*@Test
	@DisplayName("Testa a atualização da pontuação")
	void pontMethodTest() {
		ResponseEntity<Integer> response = rankingController.atualizarPontuacao(1, 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(27, response.getBody()); // Ou o valor esperado após a atualização
	}*/

	@Test
	public void testAtualizarPontuacaoSuccess() {
		// Create a sample RankingEntity object for testing
		RankingEntity ranking = new RankingEntity();
		ranking.setId(1);
		ranking.setPontuacaoTotal(100);

		// Mock the behavior of rankingRepository.findById(atletica_id)
		when(rankingRepository.findById(1)).thenReturn(Optional.of(ranking));

		// Call the method and assert the result
		ResponseEntity<Integer> response = rankingController.atualizarPontuacao(1, 50);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		//assertEquals(150, response.getBody().intValue());
	}

	@Test
	public void testAtualizarPontuacaoNotFound() {
		
		RankingEntity ranking = new RankingEntity();
		ranking.setId(1);
		ranking.setPontuacaoTotal(100);
		// Mock the behavior of rankingRepository.findById(atletica_id)
		when(rankingRepository.findById(ranking.getId())).thenReturn(Optional.empty());

		// Call the method and assert the result
		ResponseEntity<Integer> response = rankingController.atualizarPontuacao(2, 50);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNull(response.getBody());
	}

}
