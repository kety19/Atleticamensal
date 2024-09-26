package com.atletica.mensal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Entities.RankingEntity;
import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Repository.UserRepository;
import com.atletica.mensal.Service.UserService;

@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	/*	RankingEntity ranking = new RankingEntity();
		ranking.setAtletica_id(5L);
		ranking.setPontuacao_total(154);

		when(rankingRepository.findById(5L)).thenReturn(Optional.of(ranking));
		when(rankingRepository.save(any(RankingEntity.class))).thenReturn(ranking);
		
		

		rankingService.atualizarPontuacao(5L, 154);*/
	
	/*@Test
	@DisplayName("teste user email")
	void testarUserEmail() {
		
		 UserEntity usuario = new UserEntity();
	        usuario.setId(1L);
	        usuario.setNome("Atlética Tucanos");

	        when(userRepository.findById(1L)).thenReturn(Optional.of(usuario));
	        doNothing().when(userRepository).delete(usuario);

	        userService.deletarUser(1L);

	        verify(userRepository, times(1)).delete(usuario);
		
	}*/

}
